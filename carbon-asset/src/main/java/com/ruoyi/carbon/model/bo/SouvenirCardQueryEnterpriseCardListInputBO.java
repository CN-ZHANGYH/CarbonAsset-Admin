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
public class SouvenirCardQueryEnterpriseCardListInputBO {
  private String _enterpriseName;

  public List<Object> toArgs() {
    List args = new ArrayList();
    args.add(_enterpriseName);
    return args;
  }
}
