package com.forex.kolodziejek.client.client;

import java.math.BigDecimal;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.forex.kolodziejek.client.client.dao.*;
import com.forex.kolodziejek.client.client.services.WebService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.forex.kolodziejek.client.client.entities.Accounts;
import com.forex.kolodziejek.client.client.entities.ActiveTrades;
import com.forex.kolodziejek.client.client.entities.Brokers;
import com.forex.kolodziejek.client.client.entities.Candles;
import com.forex.kolodziejek.client.client.entities.Currencies;
import com.forex.kolodziejek.client.client.entities.CurrenciesRate;
import com.forex.kolodziejek.client.client.entities.Interval;
import com.forex.kolodziejek.client.client.entities.Results;
import com.forex.kolodziejek.client.client.entities.Strategies;
import com.forex.kolodziejek.client.client.entities.Symbols;
import com.forex.kolodziejek.client.client.entities.Trades;
import com.forex.kolodziejek.client.client.entities.User;
import com.forex.kolodziejek.client.client.services.SecurityService;
import com.forex.kolodziejek.client.client.services.UserService;


@RestController

public class RequestController {
	
	   private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	
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
	@Autowired
	private ResultsDao resultsDao;
	@Autowired
	private ActiveTradesDao activeTradesDao;
	@Autowired
	private WebService webService;
	@Autowired
    private UserDao userDao;


	@RequestMapping(value = "/getrate", method = RequestMethod.GET)
	public Map<String, String> getRate(@RequestParam  String date, @RequestParam String base, @RequestParam String target){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
		try {
			CurrenciesRate  currenciesRate = currencyRateDao.findByCurrencyBaseAndCurrencyTargetAndDate(currencyDao.findByName(base), currencyDao.findByName(target), simpleDateFormat.parse(date));
			return currenciesRate.asMap();
		} catch (ParseException e) {
			e.printStackTrace();
			return new HashMap<>();
		}
	}

	@RequestMapping(value = "/get_all_strategies")
    public Map<String, Map<String, String>> getAllStrategies(){
	    List<Strategies> strategies = (List<Strategies>) strategyDao.findAll();
        Map<String, Map<String, String>> resuts= new HashMap<>();
        strategies.forEach(s -> resuts.put(String.valueOf(s.getId()), s.asJson()));
        return resuts;
    }

	@RequestMapping(value = "/get_all_symbols", method = RequestMethod.GET)
    public Map<String, String> getAllSymbols(){
	    List<Symbols> symbols = (List<Symbols>) symboldao.findAll();
	    Map<String, String> resuts= new HashMap<>();
	    symbols.forEach(s -> resuts.put(String.valueOf(s.getId()), s.getSymbol_name()));
	    return resuts;
    }

	@RequestMapping(value = "/get_account", method = RequestMethod.GET)
	public Map<String, String> getAccount(@RequestParam String name){
		Accounts account = accountDao.findByName(name);
		return account.asMap();
	}

	@RequestMapping(value = "/closeAll", method = RequestMethod.GET)
    public void closeAll( @RequestParam String close){
	    List<ActiveTrades> activeTrades = (List<ActiveTrades>) activeTradesDao.findAll();
	    activeTrades.forEach(at -> { close(at, close);});
    }

    @RequestMapping(value = "/updatestoploss", method = RequestMethod.GET)
    public void updateStopLoss(@RequestParam String date, @RequestParam String stoploss, @RequestParam String stoplosstype){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss:S");
        try {
            ActiveTrades activeTrades = activeTradesDao.getByOpen(simpleDateFormat.parse(date));
            activeTrades.setStoploss(new BigDecimal(stoploss));
            activeTrades.setStoplosstype(stoplosstype);
            activeTradesDao.save(activeTrades);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void updateTrade(@RequestParam String date, @RequestParam String result){
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss:S");
        try {
            ActiveTrades activeTrades = activeTradesDao.getByOpen(simpleDateFormat.parse(date));
            activeTrades.setStatus(new BigDecimal(result));
            activeTradesDao.save(activeTrades);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
	 
	
	@RequestMapping(value="/getcandles", method=RequestMethod.GET)
	@ResponseBody
	public Map<Integer, String> getcandles(@RequestParam String start, @RequestParam String end, @RequestParam String symbol, @RequestParam String interval){
	SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH:mm");
	try {
		List<Candles> candles = candlesDao.findAllByDateBetweenAndSymbolIDAndIntervalOrderByDateAsc(df.parse(start), df.parse(end), symboldao.findByName(symbol), intervalDao.findByInterval(interval));
		Map<Integer, String> candlesJson = new HashMap<>();
		candles.forEach((c) -> {  candlesJson.put(new Integer(candles.indexOf(c)) , c.toString()); }  );
		Map<Integer, String> newMapSortedByKey = candlesJson.entrySet().stream().sorted(Map.Entry.<Integer,String>comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		return newMapSortedByKey;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
	
	@RequestMapping(value="/insertrusltes", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertResultes(@RequestParam String strategy, @RequestParam String symbol, @RequestParam String var, @RequestParam String e_payoff, @RequestParam String date, @RequestParam String borker, @RequestParam String interval) {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			resultsDao.save(new Results((new BigDecimal(var)),
					                    (new BigDecimal(e_payoff)),
					                     df.parse(date),
					                     intervalDao.findByInterval(interval),
					                     strategyDao.findByStrategyName(strategy),
					                     symboldao.findByName(symbol),
					                     getLoggedIn(),
					                     brokerDao.findByName(borker)));
			return true;
			
			
			
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}

	@RequestMapping(value = "/insertactivetrades", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertCurrentTrades(@RequestParam String date_open,  @RequestParam String type, @RequestParam String open_price, @RequestParam String strategy, @RequestParam String symbol, @RequestParam String status, @RequestParam String account, @RequestParam String stoploss, @RequestParam String interval, @RequestParam String stoploss_type, @RequestParam String size ) {

		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername("test");
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
			activeTradesDao.save(new ActiveTrades(df.parse(date_open), new BigDecimal(status), type, new BigDecimal(stoploss), stoploss_type, new BigDecimal(open_price), strategyDao.findByStrategyName(strategy), symboldao.findByName(symbol),user, intervalDao.findByInterval(interval), accountDao.findByName(account), new BigDecimal(size)));
			return true;
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage());
			return false;
		}
		
	}
	@RequestMapping(value="/inserttrades", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertTrades(@RequestParam String date_open,  @RequestParam String strategy, @RequestParam String symbol, @RequestParam String effect, @RequestParam String account, @RequestParam String date_close, @RequestParam String interval ) {
		try {

			User user = getLoggedIn();
			SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
			tradesDao.save(new Trades((new BigDecimal(effect)),df.parse(date_open),df.parse(date_close),strategyDao.findByStrategyName(strategy), symboldao.findByName(symbol),user, intervalDao.findByInterval(interval),accountDao.findByName(account)));
			return true;
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}
	
	@RequestMapping(value="/insertstrategy", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertStrategy(@RequestParam String strategyName, @RequestParam String location) {
		try {
			User user = getLoggedIn();
			User users = userDao.findByUsername(user.getUsername());
			strategyDao.save(new Strategies(strategyName,users, location));
			return true;
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}
	
	
	@RequestMapping(value="/closeTrade", method=RequestMethod.GET)
	@ResponseBody
	public void closeTrade(@RequestParam String date, @RequestParam String strategyName, @RequestParam String closeDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
		try {
			ActiveTrades activeTrade = activeTradesDao.getByOpenAndStrategy(simpleDateFormat.parse(date), strategyDao.findByStrategyName(strategyName));
			close(activeTrade, closeDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value="/insertaccount", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertAccount(@RequestParam String lavarage, @RequestParam String deposit, @RequestParam String currency, @RequestParam String broker, @RequestParam String name ) {
		try{
			BigDecimal lav = new BigDecimal(lavarage);
			BigDecimal dep = new BigDecimal(deposit);
			Currencies cur = currencyDao.findByName(currency);

			Brokers brok = brokerDao.findByName(broker);
			accountDao.save(new Accounts(name, lav, dep, brok, userService.findByUsername("test"), cur ));
			return true;
			
			
		}
		catch(Exception e){
			logger.error(e.getLocalizedMessage());
			return false;
			
		}
	}
	
	
	@RequestMapping(value="/insertrate", method=RequestMethod.GET)
	@ResponseBody
	public boolean insertRate(@RequestParam String base, @RequestParam String target, @RequestParam String rate, @RequestParam String date, @RequestParam String account) {
		if (base.equals(target))
			return false;
		else {
			try {
				Brokers borokers = accountDao.findByName(account).getBroker();
				Currencies baseCur = currencyDao.findByName(base);
				Currencies targetCur = currencyDao.findByName(target);
				BigDecimal curRate = new BigDecimal(rate);
				SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
				Date rateDate = df.parse(date);
				currencyRateDao.save(new CurrenciesRate(curRate, rateDate, new BigDecimal(0), borokers, baseCur, targetCur));
				return true;

				
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
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
			logger.error(e.getLocalizedMessage());
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
			logger.error(e.getLocalizedMessage());
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
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}
	
@GetMapping(value="/insertcandle")
	public boolean insertCandle(@RequestParam(value="interval") String interval, @RequestParam(value="date") String date, @RequestParam(value="symbol") String symbol, @RequestParam(value="high") String high, @RequestParam(value="low") String low, @RequestParam(value="open") String open, @RequestParam(value="close") String close) {
	try {
		Interval inter= intervalDao.findByInterval(interval);
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy.MM.dd-HH:mm:ss");
		Date date_close = dateformat.parse(date);
		Symbols sym = symboldao.findByName(symbol);
		BigDecimal h = new BigDecimal(high);
		BigDecimal l = new BigDecimal(low);
		BigDecimal o = new BigDecimal(open);
		BigDecimal c = new BigDecimal(close);
		candlesDao.save(new Candles(inter, date_close, sym, h, l, o, c));
		return true;
		
	} catch (Exception e) {
		logger.error(e.getLocalizedMessage());
		return false;
	}
	
}

    @RequestMapping(value = "/getdata", method = RequestMethod.GET)
	public Map<String, String> getData (@RequestParam String symbol, @RequestParam String strategy){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");

		List<Trades> trades = tradesDao.findAllByStrategyAndSymbol( strategyDao.findByStrategyName(strategy),symboldao.findByName(symbol));
		Map<String, String> results = new HashMap<>();
		trades.forEach(trade -> results.put(dateFormat.format(trade.getOpen()), trade.getEffect().toString()));
		return results;
	}

	@RequestMapping(value = "/getpoint", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, String> getPoint(@RequestParam String symbol){
		Map<String,String> result = new HashMap<>();
		result.put("point",symboldao.findByName(symbol).getPoint().toString() );
		return result;
	}
	
	
	@RequestMapping(value="/insertsymbol", method = RequestMethod.GET)
	@ResponseBody
	public boolean insertSymbol(@RequestParam String symbol, @RequestParam String point) {
		try{symboldao.save(new Symbols(symbol, new BigDecimal(point)));
		return true;}
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		 return false;
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ResponseBody
	public boolean login(@RequestParam String username, @RequestParam String pass ) {
    try { securityService.autologin(username, pass);   return true;}
    catch (Exception e) {
    	logger.error(e.getLocalizedMessage());
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
    private User getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername (((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());
    }


    private void close(ActiveTrades activeTrade, String closeDate){
		try{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
		Accounts account=activeTrade.getAccount();
		Map<String,String> params = new HashMap<>();
		params.put("before", account.getDeposit().toString());
		params.put("status", activeTrade.getStatus().toString());
		JSONObject jsonObject = webService.getJson("http://localhost:2137", params, "getaccountresult");
		account.setDeposit(new BigDecimal((String) jsonObject.get("balance")));
		tradesDao.save(new Trades(new BigDecimal((String) jsonObject.get("effect")),activeTrade.getOpen(), simpleDateFormat.parse(closeDate), activeTrade.getStrategy(), activeTrade.getSymbol(), activeTrade.getUsers(), activeTrade.getInterval(), activeTrade.getAccount()));
		activeTradesDao.delete(activeTrade);
		} catch (ParseException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			logger.debug(e.getMessage());
			e.printStackTrace();
		}

	}

}
