package com.hz.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.hz.R;
import com.hz.activity.base.BaseAttributeActivity;
import com.hz.common.Constans;
import com.hz.dialog.PickerListViewDialog;
import com.hz.greendao.dao.ConductorWireEntity;
import com.hz.greendao.dao.ConductorWireEntityDao;
import com.hz.greendao.dao.DaoSession;
import com.hz.greendao.dao.MapLineEntity;
import com.hz.greendao.dao.MapLineItemEntity;
import com.hz.greendao.dao.WireType;
import com.hz.greendao.dao.WireTypeDao;
import com.hz.entity.PickerItem;
import com.hz.util.SharedPreferencesUtils;
import com.hz.view.ValidaterEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 拉线，导线，电缆 属性编辑页面
 */
public class LineAttributeActivity extends BaseAttributeActivity {
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public static final String TAG = LineAttributeActivity.class.getSimpleName();
    public static final String LINE_NAME = "line_name";
    private MapLineEntity mapObj = null;
    private ValidaterEditText mEditWireType;//点位跨越线类型
    private ValidaterEditText mEditSpecificationNumber;//规格线数
    private TextView mEditLineLength;//导线/电缆长度
    private TableLayout mEditElectricCableTableLayout;//导线/电缆 属性
    private ArrayList<MapLineEntity> list_mapObj;

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_attribute);
    }

    @Override
    public void pickerDialogHasFocus(PickerListViewDialog pickerScrollViewDialog) {
        List<PickerItem> pickerItems = new ArrayList<>();
        String title = "";

        switch (pickerScrollViewDialog.getPickerDialogBindEditTextId()) {
            case R.id.id_edit_wiretype:
                WireTypeDao wireTypeDao = this.getDaoSession().getWireTypeDao();
                List<WireType> wireTypeList = wireTypeDao.queryBuilder()
                        .list();
                for (WireType wireType : wireTypeList) {
                    pickerItems.add(new PickerItem(wireType.getId() + "", wireType.getCrossingLineName()));
                }
                title = getResources().getString(R.string.string_crossline);
                break;
            case R.id.id_edit_electriccable_itemmode:
                ConductorWireEntityDao conductorWireEntityDao = this.getDaoSession().getConductorWireEntityDao();
                List<ConductorWireEntity> conductorWireEntityList = conductorWireEntityDao.queryBuilder()
                        .where(ConductorWireEntityDao.Properties.AreaId.eq(projectEntity.getAreaId()))
                        .where(ConductorWireEntityDao.Properties.VoltageType.eq(projectEntity.getVoltageType()))
                        .where(ConductorWireEntityDao.Properties.AreaType.eq(projectEntity.getAreaType()))
                        .list();
                Log.d(TAG, "pickerDialogHasFocus: 导线/电缆 个数：" + conductorWireEntityList.size() + "  " + projectEntity.toString());
                for (ConductorWireEntity conductorWireEntity : conductorWireEntityList) {
                    Log.d(TAG, "pickerDialogHasFocus: " + conductorWireEntity.toString());
                    pickerItems.add(new PickerItem(conductorWireEntity.getId() + "", conductorWireEntity.getMaterialNameEn()));
                }
                title = getResources().getString(R.string.string_wire_electriccable);
                break;
        }

        pickerScrollViewDialog.setPickerDialogDatas(pickerItems);
        pickerScrollViewDialog.show();
        pickerScrollViewDialog.setTitle(title);
    }
    @Override
    public void onBeforeRightIconClick() {

            mapObj.setLineEditType(Constans.AttributeEditType.EDIT_TYPE_REMOVE);

    }

    /**
     * 点击完成按钮
     */
    @Override
    public void onSetUpResult() {
        //移除默认图片
       /* mGalleryEntityList.remove(mGalleryEntityList.size() - 1);
        mapObj.setPointGalleryLists(mGalleryEntityList);*/
        mapObj.setLineWireTypeId(getString(mEditWireType.getTag()));
        mapObj.setLineName(mEditAttributeName.getText().toString());
        SharedPreferencesUtils.setParam(this, LINE_NAME, mEditAttributeName.getText().toString());
        mapObj.setLineNote(mEditAttributeNote.getText().toString());
        mapObj.setLineRemoved(Constans.RemoveIdentified.REMOVE_IDENTIFIED_NORMAL);
        mapObj.setLineLength(Double.parseDouble(String.valueOf(mEditLineLength.getText().toString())));
        mapObj.setLineSpecificationNumber(Integer.parseInt(mEditSpecificationNumber.getText().toString()));

        List<MapLineItemEntity> lineItemEntityList = new ArrayList<>();
        for (TableRow tableRow : findElectricCableTableLayoutChildTableRow()) {
            ValidaterEditText electricCable = (ValidaterEditText) tableRow.findViewById(R.id.id_edit_electriccable_itemmode);
            AppCompatSpinner wiretypeSpinner = (AppCompatSpinner) tableRow.findViewById(R.id.id_edit_spinner_linewiretype);
            AppCompatSpinner lineStatusSpinner = (AppCompatSpinner) tableRow.findViewById(R.id.id_edit_spinner_linestatus);
            ValidaterEditText lineItemNum = (ValidaterEditText) tableRow.findViewById(R.id.id_edittext_itemnum);

            //mode
            String lineItemModeId = getString(electricCable.getTag());

            //wiretype
            String wireTypeStr = getString(wiretypeSpinner.getSelectedItem());
            String wire = this.getResources().getStringArray(R.array.string_edit_linewiretype)[0];
            int wireType = TextUtils.equals(wireTypeStr, wire) ? Constans.LineWireType.WIRE : Constans.LineWireType.ELECTRIC_CABLE;

            //lineStatus
            String lineStatusStr = getString(lineStatusSpinner.getSelectedItem());
            String status = this.getResources().getStringArray(R.array.string_edit_linestatus)[0];
            int lineStatus = TextUtils.equals(lineStatusStr, status) ? Constans.AttributeStatus.NEW : Constans.AttributeStatus.OLD;

            //num
            String lintNumStr = lineItemNum.getText().toString();
            int itemNum = TextUtils.isEmpty(lintNumStr) ? 1 : Integer.parseInt(lintNumStr);

            //hide id
            TextView hideIdTextView = (TextView) tableRow.findViewById(R.id.id_textview_hide_row_id);
            String hideId = hideIdTextView.getText().toString();

            //remove identifier
            int removeIdentifier = (tableRow.getVisibility() == View.VISIBLE) ? Constans.RemoveIdentified.REMOVE_IDENTIFIED_NORMAL : Constans.RemoveIdentified.REMOVE_IDENTIFIED_REMOVED;

            Log.d(TAG, lineItemModeId + "  " + wireTypeStr + "  " + lineStatusStr + "  " + itemNum + "  " + hideId + "  " + removeIdentifier);

            MapLineItemEntity itemEntity = new MapLineItemEntity();
            itemEntity.setLineItemWireType(wireType);
            Log.d("KO", itemEntity.getLineItemWireType()+"  1");
            itemEntity.setLineItemModeId(lineItemModeId);
            Log.d("KO", itemEntity.getLineItemModeId()+"    2");
            itemEntity.setLineItemNum(itemNum);
            Log.d("KO", itemEntity.getLineItemNum()+"   3");
            itemEntity.setLineItemId(hideId);
            Log.d("KO", itemEntity.getLineItemId()+"    4");
            itemEntity.setLineItemStatus(lineStatus);
            Log.d("KO", itemEntity.getLineItemStatus()+"    5");
            itemEntity.setLineItemRemoved(removeIdentifier);
            Log.d("KO", itemEntity.getLineItemRemoved()+"   6");
            lineItemEntityList.add(itemEntity);
        }
        Log.d("KO", lineItemEntityList.size()+" 7");
        mapObj.setMapLineItemEntityList(lineItemEntityList);

        //设置bundle
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constans.LINE_OBJ_KEY, mapObj);
        Log.d("KO", mapObj.getLineName()+"  8");
        //设置intent
        Intent resultIntent = new Intent();
        resultIntent.putExtras(bundle);

        //设置返回信息
        this.setResult(Constans.RequestCode.LINE_ATTRIBUTE_EDIT_REQUESTCODE, resultIntent);
    }
    @Override
    public boolean onValidateInputSetUpResult() {
        int lineType = mapObj.getLineType();
        List<ValidaterEditText> validateList = new ArrayList<>();
        validateList.add(mEditAttributeName);
        validateList.add(mEditAttributeNote);

        switch (lineType) {
            case Constans.MapAttributeType.CROSS_LINE://跨越线
                validateList.add(mEditWireType);
                break;
            case Constans.MapAttributeType.WIRE_ELECTRIC_CABLE:
                for (TableRow tableRow : findElectricCableTableLayoutChildTableRow()) {
                    if (tableRow.getVisibility() == View.VISIBLE) {
                        validateList.add((ValidaterEditText) tableRow.findViewById(R.id.id_edit_electriccable_itemmode));
                        validateList.add((ValidaterEditText) tableRow.findViewById(R.id.id_edittext_itemnum));
                    }
                }
                validateList.add(mEditSpecificationNumber);
                break;
        }
        boolean allValid = true;
        for (ValidaterEditText field : validateList) {
            allValid = field.validateByConfig() && allValid;
        }
        return allValid;
    }
    @Override
    public void onAnalysisBundleData() {
        super.onAnalysisBundleData();
        //获取传入参数
        Bundle bundleParam = this.getIntent().getExtras();
        mapObj = (MapLineEntity) bundleParam.getSerializable(Constans.LINE_OBJ_KEY);
        Log.d("KO", "执行力看1");
        if (mapObj == null) {
            list_mapObj = (ArrayList<MapLineEntity>)(bundleParam.getSerializable(Constans.LINE_OBJ_KEY_TEST));
            if(list_mapObj == null)
                analysisUiTitleAndFieldVisibleByPointType(list_mapObj.get(0).getLineType());
                return;
        }
        Log.d("KO", "执行力看2");
        //根据线类型显示不同的标题和控制组件的显示隐藏
        analysisUiTitleAndFieldVisibleByPointType(mapObj.getLineType());
        //根据参数显示修改或者新增
        displayOkOrUpdateByAttributeByEditType(mapObj.getLineEditType());
        //设置tag和文本
        setTextFieldTagAndText(mapObj);
        //根据textfield的tag到数据库查询数据库文本信息
        setTextFieldTextByTextFieldTag(mapObj);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.id_button_addtablerow://添加一行
                TableRow tableRow = createNewTableRow();
                addNewTableRowToTableLayout(tableRow);
                break;
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    private void setTextFieldTextByTextFieldTag(MapLineEntity mapObj) {
        DaoSession daoSession = this.getDaoSession();
        //拉线类型
        if (mapObj.getLineWireTypeId() != null && mEditWireType.getVisibility() == View.VISIBLE) {
            WireType wireType = daoSession
                    .getWireTypeDao()
                    .queryBuilder()
                    .where(WireTypeDao.Properties.Id.eq(mapObj.getLineWireTypeId()))
                    .unique();
            if (wireType != null) {
                mEditWireType.setText(wireType.getCrossingLineName());
            }
        }
    }
    /**
     * 设置文本域标签和文本
     */
    private void setTextFieldTagAndText(MapLineEntity mapObj) {
        //设置名称和备注
        setNameAndNoteByBundleData(mapObj.getLineName(), mapObj.getLineNote());
        mEditWireType.setTag(mapObj.getLineWireTypeId());

        int num = mapObj.getLineSpecificationNumber();
        mEditSpecificationNumber.setText(num == 0 ? getResources().getString(R.string.string_num_one) : String.valueOf(num));


        LatLng startLatlong = new LatLng(mapObj.getLineStartLatitude(), mapObj.getLineStartLongitude());
        LatLng endLatlong = new LatLng(mapObj.getLineEndLatitude(), mapObj.getLineEndLongitude());
        mEditLineLength.setText(Constans.DECIMALFORMAT_M.format(DistanceUtil.getDistance(startLatlong, endLatlong)) + "");
    }
    /**
     * 根据点类型来分析UI标题和可见区域
     */
    private void analysisUiTitleAndFieldVisibleByPointType(int lineType) {
        View wiretype = findViewById(R.id.id_linearlayout_wiretype);
        wiretype.setVisibility(View.GONE);
        View electricCable = findViewById(R.id.id_linearlayout_electriccable);
        electricCable.setVisibility(View.GONE);
        View linelength = findViewById(R.id.id_linearlayout_linelength);
        linelength.setVisibility(View.GONE);
        View specificationNumber = findViewById(R.id.id_linearlayout_specificationnumber);
        specificationNumber.setVisibility(View.GONE);

        ArrayList<View> animateVeiwVisible = new ArrayList<>();

        switch (lineType) {
            case Constans.MapAttributeType.CROSS_LINE://跨越线
                setMDToolBarTitle(R.string.string_crossline);
                animateVeiwVisible.add(wiretype);
                break;
            case Constans.MapAttributeType.WIRE_ELECTRIC_CABLE:
                if (mapObj.getLineEditType() == Constans.AttributeEditType.EDIT_TYPE_LINE_BATCHADD) {
                    setMDToolBarTitle(R.string.string_batch_wire_electriccable);
                } else if(mapObj.getLineEditType() == Constans.AttributeEditType.EDIT_TYPE_LINE_BATCHADD_C){
                    setMDToolBarTitle(R.string.change_all);
                } else {
                    setMDToolBarTitle(R.string.string_wire_electriccable);
                    animateVeiwVisible.add(linelength);
                }
                animateVeiwVisible.add(specificationNumber);
                animateVeiwVisible.add(electricCable);

                //修改时
                if (mapObj.getLineEditType() == Constans.AttributeEditType.EDIT_TYPE_EDIT) {
                    List<MapLineItemEntity> mapLineItemEntityList = mapObj.getMapLineItemEntityList();
                    if (mapLineItemEntityList != null && mapLineItemEntityList.size() > 0) {
                        for (MapLineItemEntity lineItemEntity : mapLineItemEntityList) {
                            TableRow tableRow = createNewTableRowWithData(lineItemEntity);
                            animateVeiwVisible.add(tableRow);
                        }
                    }
                }
                break;
        }

        for (View view : animateVeiwVisible) {
            if (view instanceof TableRow) {
                addNewTableRowToTableLayout((TableRow) view);
            } else {
                view.setVisibility(View.VISIBLE);
            }
        }
    }
    /**
     * 添加一行
     */
    private TableRow createNewTableRowWithData(MapLineItemEntity lineItemEntity) {
        TableRow tableRow = createNewTableRow();

        //初始化线模
        ValidaterEditText itemModeEditText = (ValidaterEditText) tableRow.findViewById(R.id.id_edit_electriccable_itemmode);
        itemModeEditText.setTag(lineItemEntity.getLineItemModeId());

        DaoSession daoSession = this.getDaoSession();
        //在线程中根据传入的输入框ID更新显示tett信息
        //更新杆塔显示信息
        if (lineItemEntity.getLineItemModeId() != null && itemModeEditText.getVisibility() == View.VISIBLE) {
            ConductorWireEntity conductorWireEntity = daoSession
                    .getConductorWireEntityDao()
                    .queryBuilder()
                    .where(ConductorWireEntityDao.Properties.Id.eq(lineItemEntity.getLineItemModeId()))
                    .unique();
            if (conductorWireEntity != null) {
                itemModeEditText.setText(conductorWireEntity.getMaterialNameEn());
            }
        }

        AppCompatSpinner lineWireType = (AppCompatSpinner) tableRow.findViewById(R.id.id_edit_spinner_linewiretype);
        lineWireType.setSelection(lineItemEntity.getLineItemWireType());

        AppCompatSpinner lineStatus = (AppCompatSpinner) tableRow.findViewById(R.id.id_edit_spinner_linestatus);
        lineStatus.setSelection(lineItemEntity.getLineItemStatus());

        ValidaterEditText lineItemNum = (ValidaterEditText) tableRow.findViewById(R.id.id_edittext_itemnum);
        lineItemNum.setText("" + lineItemEntity.getLineItemNum());

        //hide id
        TextView textView = (TextView) tableRow.findViewById(R.id.id_textview_hide_row_id);
        textView.setText(lineItemEntity.getLineItemId());

        //show or hide by removeIdentifier
        tableRow.setVisibility((lineItemEntity.getLineItemRemoved() == Constans.RemoveIdentified.REMOVE_IDENTIFIED_REMOVED) ? View.GONE : View.VISIBLE);
        return tableRow;
    }
    /**
     * 创建一行
     */
    private TableRow createNewTableRow() {
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tableRow = (TableRow) inflater.inflate(R.layout.tablerow_item, null);

        //初始化线模
        ValidaterEditText validaterEditText = (ValidaterEditText) tableRow.findViewById(R.id.id_edit_electriccable_itemmode);
        PickerListViewDialog mWireTypePickerScrollViewDialog = new PickerListViewDialog(this);
        mWireTypePickerScrollViewDialog.setPickerDialogBindEditText(validaterEditText);
        mWireTypePickerScrollViewDialog.setOnPickerDialogHasFocusListener(this);

        //初始化删除本行视图
        View deleteView = tableRow.findViewById(R.id.id_tablerow_deleterow);
        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableRow toDeleteTableRow = (TableRow) v.getParent();
                toDeleteTableRow.setVisibility(View.GONE);
            }
        });

        //hide id
        TextView textView = (TextView) tableRow.findViewById(R.id.id_textview_hide_row_id);
        textView.setText(UUID.randomUUID().toString());//set default id
        return tableRow;
    }
    /**
     * 添加一行
     */
    private void addNewTableRowToTableLayout(TableRow tableRow) {
        mEditElectricCableTableLayout.addView(tableRow, mEditElectricCableTableLayout.getChildCount() - 1);
    }
    /**
     * 获取ElectricCable的所有行
     */
    private List<TableRow> findElectricCableTableLayoutChildTableRow() {
        List<TableRow> tableRowList = new ArrayList<>();
        int childCount = mEditElectricCableTableLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View tableRow = mEditElectricCableTableLayout.getChildAt(i);
            if (tableRow != null && tableRow instanceof TableRow) {
                tableRowList.add((TableRow) tableRow);
            }
        }
        return tableRowList;
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    @Override
    public void onInitView() {
        super.onInitView();
        setMdToolBar(R.id.id_material_toolbar);
        setMDToolBarBackEnable(true);
        setMDToolBarTitle(R.string.title_line_attribute);
        //跨越线属性
        mEditWireType = (ValidaterEditText) findViewById(R.id.id_edit_wiretype);

        mEditSpecificationNumber = (ValidaterEditText) findViewById(R.id.id_edit_specificationnumber);

        //tableLayout
        mEditElectricCableTableLayout = (TableLayout) findViewById(R.id.id_tablelayout_electriccable);
        //线长度
        mEditLineLength = (TextView) findViewById(R.id.id_edit_linelength);


        //拉线/导线属性
        Button addNewRowButton = (Button) mEditElectricCableTableLayout.findViewById(R.id.id_button_addtablerow);
        addNewRowButton.setOnClickListener(this);

        //初始化pickerDialog选择数据插件
        PickerListViewDialog mWireTypePickerScrollViewDialog = new PickerListViewDialog(this);
        mWireTypePickerScrollViewDialog.setPickerDialogBindEditText(mEditWireType);
        mWireTypePickerScrollViewDialog.setOnPickerDialogHasFocusListener(this);
    }
}
