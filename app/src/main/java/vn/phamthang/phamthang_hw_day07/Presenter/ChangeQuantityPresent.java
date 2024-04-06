package vn.phamthang.phamthang_hw_day07.Presenter;

import android.content.Context;

import vn.phamthang.phamthang_hw_day07.Helper.Database;
import vn.phamthang.phamthang_hw_day07.Interface.IOnChangeQuantityModel;
import vn.phamthang.phamthang_hw_day07.Interface.IOnChangeQuantityView;

public class ChangeQuantityPresent implements IOnChangeQuantityModel {
    private IOnChangeQuantityView iOnChangeQuantityView;
    private Database database;
    private Context mContext;

    public ChangeQuantityPresent(Context context, IOnChangeQuantityView iOnChangeQuantityView) {
        this.mContext = context;
        this.iOnChangeQuantityView = iOnChangeQuantityView;
        this.database = new Database(context);
        this.database.setiOnChangeQuantityModel(this);
    }

    public void onChangePLus(int id) {
        database.onClickPlus(id);
    }

    @Override
    public void onClickPlus(int id) {
        iOnChangeQuantityView.onClickPlus(id);
    }

    @Override
    public void onClickMinus() {

    }
}
