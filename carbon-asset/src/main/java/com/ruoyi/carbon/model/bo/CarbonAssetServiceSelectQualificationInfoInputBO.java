package com.ruoyi.carbon.model.bo;

import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarbonAssetServiceSelectQualificationInfoInputBO {
  private String _enterpriseAddress;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseAddress);
    return args;
  }
}
