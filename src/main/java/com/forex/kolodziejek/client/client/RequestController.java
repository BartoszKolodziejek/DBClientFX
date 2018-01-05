package com.forex.kolodziejek.client.client;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forex.kolodziejek.client.client.dao.AccountDao;
import com.forex.kolodziejek.client.client.dao.BrokerDao;
import com.forex.kolodziejek.client.client.dao.CandlesDao;
import com.forex.kolodziejek.client.client.dao.CurrencyDao;
import com.forex.kolodziejek.client.client.dao.CurrencyRateDao;
import com.forex.kolodziejek.client.client.dao.IntervalDao;
import com.forex.kolodziejek.client.client.dao.StrategyDao;
import com.forex.kolodziejek.client.client.dao.SymbolDao;
import com.forex.kolodziejek.client.client.dao.TradesDao;
import com.forex.kolodziejek.client.client.entities.Accounts;
import com.forex.kolodziejek.client.client.entities.Brokers;
import com.forex.kolodziejek.client.client.entities.Candles;
import com.forex.kolodziejek.client.client.entities.Currencies;
import com.forex.kolodziejek.client.client.entities.CurrenciesRate;
import com.forex.kolodziejek.client.client.entities.Interval;
import com.forex.kolodziejek.client.client.entities.Strategies;
import com.forex.kolodziejek.client.client.entities.Symbols;
import com.forex.kolodziejek.client.client.entities.Trades;
import com.forex.kolodziejek.client.client.entities.User;
import com.forex.kolodziejek.client.client.services.SecurityService;
import com.forex.kolodziejek.client.client.services.UserService;


@RestController
@Transactional 
public class RequestController {
	
	@Autowired
	private SymbolDao symboldao; 
	@Autowired
    private SecurityService securityService;
	@Autowired
	private UserService userService;
	@Autowired
	private IntervalDao intervalDao;
	@Autowired
	private CandlesDao candlesDao;
	@Autowired
	private CurrencyDao currencyDao;
	@Autowired
	private BrokerDao brokerDao;
	@Autowired
	private CurrencyRateDao currencyRateDao;
	@Autowired
	private AccountDao accountDao;
	@Autowired
	private StrategyDao strategyDao;
	@Autowired
	private TradesDao tradesDao;
	
	@RequestMapping(value="/inserttrades", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertAccount(@RequestParam String date_open, @RequestParam String strategy, @RequestParam String symbol, @RequestParam String effect, @RequestParam String account, @RequestParam String date_close, @RequestParam String interval ) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm");
			tradesDao.save(new Trades((new BigDecimal(effect)),df.parse(date_open),df.parse(date_close),strategyDao.findByStrategyName(strategy), symboldao.findByName(symbol),user, intervalDao.findByInterval(interval),accountDao.findByName(account)));
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@RequestMapping(value="/insertstrategy", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertAccount(@RequestParam String strategyName) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			strategyDao.save(new Strategies(strategyName,user));
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	
	@RequestMapping(value="/insertaccount", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertAccount(@RequestParam String lavarage, @RequestParam String deposit, @RequestParam String currency, @RequestParam String broker, @RequestParam String name ) {
		try{
			BigDecimal lav = new BigDecimal(lavarage);
			BigDecimal dep = new BigDecimal(deposit);
			Currencies cur = currencyDao.findByName(currency);
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = (User) authentication.getPrincipal();
			Brokers brok = brokerDao.findByName(broker);
			accountDao.save(new Accounts(name, lav, dep, brok, user, cur ));
			return true;
			
			
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return false;
			
		}
	}
	
	
	@RequestMapping(value="/insertrate", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertRate(@RequestParam String broker, @RequestParam String base, @RequestParam String target, @RequestParam String rate, @RequestParam String date) {
		if (base.equals(target))
			return false;
		else {
			try {
				Brokers borokers = brokerDao.findByName(broker);
				Currencies baseCur = currencyDao.findByName(base);
				Currencies targetCur = currencyDao.findByName(target);
				BigDecimal curRate = new BigDecimal(rate);
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yy HH-mm");
				Date rateDate = df.parse(date);
				currencyRateDao.save(new CurrenciesRate(curRate, rateDate, curRate, borokers, baseCur, targetCur));
				return true;
				
				
				
				
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
			
			
		}
		
	}
	
	@RequestMapping(value="/insertbroker", method= RequestMethod.GET)
	@ResponseBody
	public boolean insertBroker(@RequestParam String name) {
		try {
			brokerDao.save(new Brokers(name));
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	
	@RequestMapping(value="/insertcurrency", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertCurrency(@RequestParam String name) {
		
		try {
			currencyDao.save(new Currencies(name));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	
	@RequestMapping(value="/insertinterval", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertInterval(@RequestParam String interval) {
		try{
			intervalDao.save(new Interval(interval));
			return true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
@RequestMapping(value="/insertcandle", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertCandle(@RequestParam String interval, @RequestParam String date, @RequestParam String symbol, @RequestParam String high, @RequestParam String low, @RequestParam String open, @RequestParam String close) {
	try {
		Interval inter= intervalDao.findByInterval(interval);
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yy HH-mm");
		Date date_close = dateformat.parse(date);
		Symbols sym = symboldao.findByName(symbol);
		BigDecimal h = new BigDecimal(high);
		BigDecimal l = new BigDecimal(low);
		BigDecimal o = new BigDecimal(open);
		BigDecimal c = new BigDecimal(close);
		candlesDao.save(new Candles(inter, date_close, sym, h, l, o, c));
		return true;
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
		return false;
	}
	
}
	
	
	@RequestMapping(value="/insertsymbol", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertSymbol(@RequestParam String symbol) {
		try{symboldao.save(new Symbols(symbol));
		return true;}
		catch (Exception e) {
			System.out.println(e.getMessage());
		 return false;
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ResponseBody
	public boolean login(@RequestParam String username, @RequestParam String pass ) {
    try { securityService.autologin(username, pass);   return true;}
    catch (Exception e) {
    	System.out.println(e.getMessage());
return false;	}
		
	}
	
	@RequestMapping(value="/newuser", method = RequestMethod.GET)
	@ResponseBody
	public boolean newuser(@RequestParam String username, @RequestParam String pass, @RequestParam String email  ) {
try {
    			
    		
    		User user = new User(username, pass, email);
    		userService.save(user);
    		securityService.autologin(user.getUsername(), user.getPass());
    		String userId = String.valueOf(user.getId());
    		System.out.println(userId);
    		return true;
    		}
    		catch(Exception ex){
    			System.out.println(ex.getMessage());
    			return false;
    		}
		
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		    if (auth != null){    
		        new SecurityContextLogoutHandler().logout(request, response, auth);
		    }
		return "redirect:/login?logout";
	}

}
