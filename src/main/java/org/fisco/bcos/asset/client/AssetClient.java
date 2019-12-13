package org.fisco.bcos.asset.client;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fisco.bcos.asset.contract.Asset;
import org.fisco.bcos.asset.contract.Asset.RegisterEventEventResponse;
import org.fisco.bcos.asset.contract.Asset.TransferEventEventResponse;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class AssetClient {

	static Logger logger = LoggerFactory.getLogger(AssetClient.class);

	private Web3j web3j;

	private Credentials credentials;
     
	public String getAccountAddress(String name){
	 	if(name=="Alice"){
			return "0x21f59f03b5a2fa9c9c6f450512d8cd50cee6f6ba";
		}
		else if(name =="Bob"){
			return "0x778ee58c65af030a9cec819026390b19dbc94471";
		}
		else if(name =="Candy"){
			return "0xbe6f0c0f3e74012019c6f741fec64319c4abe9d6";
		}
		else if(name =="bank"){
			return "0x1116a89f90e945dc0b1b8254dc389efa3cb77ea0";
		}
		else{
			return "0x00";
		}
	}
 
	public Web3j getWeb3j() {
		return web3j;
	}

	public void setWeb3j(Web3j web3j) {
		this.web3j = web3j;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	public void recordAssetAddr(String address) throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.setProperty("address", address);
		final Resource contractResource = new ClassPathResource("contract.properties");
		FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
		prop.store(fileOutputStream, "contract address");
	}

	public String loadAssetAddr() throws Exception {
		// load Asset contact address from contract.properties
		Properties prop = new Properties();
		final Resource contractResource = new ClassPathResource("contract.properties");
		prop.load(contractResource.getInputStream());

		String contractAddress = prop.getProperty("address");
		if (contractAddress == null || contractAddress.trim().equals("")) {
			throw new Exception(" load Asset contract address failed, please deploy it first. ");
		}
		logger.info(" load Asset address from contract.properties, address is {}", contractAddress);
		return contractAddress;
	}

	public void initialize() throws Exception {

		// init the Service
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Service service = context.getBean(Service.class);
		service.run();

		ChannelEthereumService channelEthereumService = new ChannelEthereumService();
		channelEthereumService.setChannelService(service);
		Web3j web3j = Web3j.build(channelEthereumService, 1);

		// init Credentials
		Credentials credentials = Credentials.create(Keys.createEcKeyPair());

		setCredentials(credentials);
		setWeb3j(web3j);

		logger.debug(" web3j is " + web3j + " ,credentials is " + credentials);
	}

	private static BigInteger gasPrice = new BigInteger("30000000");
	private static BigInteger gasLimit = new BigInteger("30000000");

	public void deployAssetAndRecordAddr() {

		try {
			Asset asset = Asset.deploy(web3j, credentials, new StaticGasProvider(gasPrice, gasLimit)).send();
			System.out.println(" deploy Asset success, contract address is " + asset.getContractAddress());

			recordAssetAddr(asset.getContractAddress());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println(" deploy Asset contract failed, error message is  " + e.getMessage());
		}
	}

	public void queryAssetBalance(String assetAccount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.getBalance(getAccountAddress(assetAccount)).send();
			List<TransferEventEventResponse> response = asset.getTransferEventEvents(receipt);
		        System.out.printf(" asset account %s, value %s \n", assetAccount, response.get(0).ret.toString());
		    }catch (Exception e) {
				System.out.printf(" failed, error message is %s\n", e.getMessage());
		}
			
	}

	public void queryAssetBond(String assetAccount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.getBond(getAccountAddress(assetAccount)).send();
			List<TransferEventEventResponse> response = asset.getTransferEventEvents(receipt);
		        System.out.printf(" asset account %s, Bond %s \n", assetAccount, response.get(0).ret.toString());
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}


	public void issueAssetAccount(String assetAccount, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.issue(getAccountAddress(assetAccount), amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" issue asset account success => asset: %s, value: %s \n", assetAccount,
							amount);
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}

	public void send_moneyAsset(String fromAssetAccount, String toAssetAccount, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.send_money(getAccountAddress(fromAssetAccount),getAccountAddress(toAssetAccount),amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" %s send money to %s success, value: %s \n",             fromAssetAccount,toAssetAccount,amount);
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}

	public void send_bondAsset(String fromAssetAccount, String toAssetAccount,String borrowerAssetAccount, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.send_bond(getAccountAddress(fromAssetAccount),getAccountAddress(toAssetAccount),
getAccountAddress(borrowerAssetAccount),amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" %s send bond to %s success, value: %s \n",             fromAssetAccount,toAssetAccount,amount);
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}
	
	public void borrowAsset(String borrower, String lander, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.borrow(getAccountAddress(borrower),getAccountAddress(lander),amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" %s borrow money from %s success, value: %s \n",borrower,lander,amount);	
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}
	}

	public void payAsset(String borrower, String lander, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.pay(getAccountAddress(borrower),getAccountAddress(lander),amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" %s pay money to %s success, value: %s \n",borrower,lander,amount);
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}

	public void financAsset(String bank, String name, BigInteger amount) {
		try{
			String contractAddress = loadAssetAddr();
			Asset asset = Asset.load(contractAddress, web3j, credentials, new StaticGasProvider(gasPrice, gasLimit));
			TransactionReceipt receipt = asset.pay(getAccountAddress(bank),getAccountAddress(name),amount).send();
			List<RegisterEventEventResponse> response = asset.getRegisterEventEvents(receipt);
			System.out.printf(" %s financ money from bank success, value: %s \n",name,amount);
		   }catch (Exception e) {
				System.out.printf("failed, error message is %s\n", e.getMessage());
		}	
	}

	public static void main(String[] args) throws Exception {


		AssetClient client = new AssetClient();
		client.initialize();

		switch (args[0]) {
		case "deploy":
			client.deployAssetAndRecordAddr();
			break;
		case "getBalance":
			client.queryAssetBalance(args[1]);
			break;
		case "getBond":
			client.queryAssetBond(args[1]);
			break;
		case "issue":
			client.issueAssetAccount(args[1], new BigInteger(args[2]));
			break;
		case "send_money":
			client.send_moneyAsset(args[1], args[2], new BigInteger(args[3]));
			break;
		case "send_bond":
			client.send_bondAsset(args[1], args[2],args[3], new BigInteger(args[4]));
			break;
		case "borrow":
			client.borrowAsset(args[1], args[2], new BigInteger(args[3]));
			break;
		case "pay":
			client.payAsset(args[1], args[2], new BigInteger(args[3]));
			break;
                case "financ":
			client.financAsset("bank", args[1], new BigInteger(args[2]));
			break;
		}

		System.exit(0);
	}
}
