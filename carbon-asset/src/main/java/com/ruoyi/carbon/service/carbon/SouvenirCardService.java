package com.ruoyi.carbon.service.carbon;

import com.ruoyi.carbon.model.bo.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.transaction.manager.AssembleTransactionProcessor;
import org.fisco.bcos.sdk.transaction.manager.TransactionProcessorFactory;
import org.fisco.bcos.sdk.transaction.model.dto.CallResponse;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@NoArgsConstructor
@Data
public class SouvenirCardService {
  public static final String ABI = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("abi/SouvenirCard.abi");

  public static final String BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/ecc/SouvenirCard.bin");

  public static final String SM_BINARY = com.ruoyi.carbon.utils.IOUtil.readResourceAsString("bin/sm/SouvenirCard.bin");

  @Value("${system.contract.souvenirCardAddress}")
  private String address;

  @Autowired
  private Client client;

  AssembleTransactionProcessor txProcessor;

  @PostConstruct
  public void init() throws Exception {
    this.txProcessor = TransactionProcessorFactory.createAssembleTransactionProcessor(this.client, this.client.getCryptoSuite().getCryptoKeyPair());
  }

  public TransactionResponse QueryCardInfo(SouvenirCardQueryCardInfoInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "QueryCardInfo", input.toArgs());
  }

  public CallResponse CardInfoMap(SouvenirCardCardInfoMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "CardInfoMap", input.toArgs());
  }

  public TransactionResponse RegisterCard(SouvenirCardRegisterCardInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "RegisterCard", input.toArgs());
  }

  public CallResponse UserOfCardListMap(SouvenirCardUserOfCardListMapInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "UserOfCardListMap", input.toArgs());
  }

  public CallResponse CardInfoList(SouvenirCardCardInfoListInputBO input) throws Exception {
    return this.txProcessor.sendCall(this.client.getCryptoSuite().getCryptoKeyPair().getAddress(), this.address, ABI, "CardInfoList", input.toArgs());
  }

  public TransactionResponse IsCardExist(SouvenirCardIsCardExistInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "IsCardExist", input.toArgs());
  }

  public TransactionResponse UserBindCard(SouvenirCardUserBindCardInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "UserBindCard", input.toArgs());
  }

  public TransactionResponse QueryEnterpriseCardList(SouvenirCardQueryEnterpriseCardListInputBO input) throws Exception {
    return this.txProcessor.sendTransactionAndGetResponse(this.address, ABI, "QueryEnterpriseCardList", input.toArgs());
  }
}
