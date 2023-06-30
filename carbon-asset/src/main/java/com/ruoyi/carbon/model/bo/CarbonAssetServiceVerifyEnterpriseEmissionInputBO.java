package com.ruoyi.carbon.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonAssetServiceVerifyEnterpriseEmissionInputBO {
  private String _regularAddress;

  private String _enterpriseAddr;

  private BigInteger _emmissionid;

  private Boolean _isApprove;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_regularAddress);
    args.add(_enterpriseAddr);
    args.add(_emmissionid);
    args.add(_isApprove);
    return args;
  }
}
