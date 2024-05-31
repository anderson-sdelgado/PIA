package br.com.usinasantafe.pia.util.retrofit;

import java.util.List;

import br.com.usinasantafe.pia.model.bean.variaveis.CabecAmostraBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AmostraDao {

    @POST("inseriramostra.php")
    Call<List<CabecAmostraBean>> envioDadosAmostra(@Body List<CabecAmostraBean> movEquipProprioList, @Header("Authorization") String auth);

}
