package com.ruoyi.carbon.model.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonAssetServiceVerifyQualificationInputBO {
  private String _regulatorAddress;

  private String _enterpriseAddress;

  private Boolean _isApprove;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_regulatorAddress);
    args.add(_enterpriseAddress);
    args.add(_isApprove);
    return args;
  }
}
