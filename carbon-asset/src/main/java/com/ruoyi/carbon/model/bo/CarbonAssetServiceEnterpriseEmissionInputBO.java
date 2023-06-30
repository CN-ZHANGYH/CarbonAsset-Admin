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
public class CarbonAssetServiceEnterpriseEmissionInputBO {
  private String _enterpriseAddr;

  private BigInteger _emmissionid;

  private BigInteger _emissionEmission;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseAddr);
    args.add(_emmissionid);
    args.add(_emissionEmission);
    return args;
  }
}
