package com.ruoyi.carbon.service.carbon;

import com.ruoyi.carbon.config.SystemConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Data
@Slf4j
public class ServiceManager {
  @Autowired
  private SystemConfig config;

  @Autowired
  private Client client;

  List<String> hexPrivateKeyList;

  @PostConstruct
  public void init() {
    hexPrivateKeyList = Arrays.asList(this.config.getHexPrivateKey().split(","));
  }

  /**
   * @notice: must use @Qualifier("SouvenirCardService") with @Autowired to get this Bean
   */
  @Bean("SouvenirCardService")
  public Map<String, SouvenirCardService> initSouvenirCardServiceManager() throws Exception {
    Map<String, SouvenirCardService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	SouvenirCardService souvenirCardService = new SouvenirCardService();
    	souvenirCardService.setAddress(this.config.getContract().getSouvenirCardAddress());
    	souvenirCardService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor =
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	souvenirCardService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, souvenirCardService);
    }
    log.info("++++++++SouvenirCardService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("CarbonUserServiceService") with @Autowired to get this Bean
   */
  @Bean("CarbonUserServiceService")
  public Map<String, CarbonUserServiceService> initCarbonUserServiceServiceManager() throws Exception {
    Map<String, CarbonUserServiceService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	CarbonUserServiceService carbonUserServiceService = new CarbonUserServiceService();
    	carbonUserServiceService.setAddress(this.config.getContract().getCarbonUserServiceAddress());
    	carbonUserServiceService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor =
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	carbonUserServiceService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, carbonUserServiceService);
    }
    log.info("++++++++CarbonUserServiceService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("CarbonDataStorageService") with @Autowired to get this Bean
   */
  @Bean("CarbonDataStorageService")
  public Map<String, CarbonDataStorageService> initCarbonDataStorageServiceManager() throws Exception {
    Map<String, CarbonDataStorageService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	CarbonDataStorageService carbonDataStorageService = new CarbonDataStorageService();
    	carbonDataStorageService.setAddress(this.config.getContract().getCarbonDataStorageAddress());
    	carbonDataStorageService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor =
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	carbonDataStorageService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, carbonDataStorageService);
    }
    log.info("++++++++CarbonDataStorageService map:{}", serviceMap);
    return serviceMap;
  }

  /**
   * @notice: must use @Qualifier("CarbonAssetServiceService") with @Autowired to get this Bean
   */
  @Bean("CarbonAssetServiceService")
  public Map<String, CarbonAssetServiceService> initCarbonAssetServiceServiceManager() throws Exception {
    Map<String, CarbonAssetServiceService> serviceMap = new ConcurrentHashMap<>(this.hexPrivateKeyList.size());
    for (int i = 0; i < this.hexPrivateKeyList.size(); i++) {
    	String privateKey = this.hexPrivateKeyList.get(i);
    	if (privateKey.startsWith("0x") || privateKey.startsWith("0X")) {
    		privateKey = privateKey.substring(2);
    	}
    	if (privateKey.isEmpty()) {
    		continue;
    	}
    	org.fisco.bcos.sdk.crypto.CryptoSuite cryptoSuite = new org.fisco.bcos.sdk.crypto.CryptoSuite(this.client.getCryptoType());
    	org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair cryptoKeyPair = cryptoSuite.createKeyPair(privateKey);
    	String userAddress = cryptoKeyPair.getAddress();
    	log.info("++++++++hexPrivateKeyList[{}]:{},userAddress:{}", i, privateKey, userAddress);
    	CarbonAssetServiceService carbonAssetServiceService = new CarbonAssetServiceService();
    	carbonAssetServiceService.setAddress(this.config.getContract().getCarbonAssetServiceAddress());
    	carbonAssetServiceService.setClient(this.client);
    	org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor txProcessor =
    		org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, cryptoKeyPair);
    	carbonAssetServiceService.setTxProcessor(txProcessor);
    	serviceMap.put(userAddress, carbonAssetServiceService);
    }
    log.info("++++++++CarbonAssetServiceService map:{}", serviceMap);
    return serviceMap;
  }
}
