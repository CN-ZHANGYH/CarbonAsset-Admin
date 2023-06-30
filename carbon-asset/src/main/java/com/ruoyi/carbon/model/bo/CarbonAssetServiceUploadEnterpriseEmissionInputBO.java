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
public class CarbonAssetServiceUploadEnterpriseEmissionInputBO {
  private String _enterpriseAddr;

  private BigInteger _emissionEmission;

  private String _description;

  private String _emissionWay;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseAddr);
    args.add(_emissionEmission);
    args.add(_description);
    args.add(_emissionWay);
    return args;
  }
}
