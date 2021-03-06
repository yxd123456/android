package com.hz.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.hz.greendao.dao.ElectricPoleType;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "ELECTRIC_POLE_TYPE".
*/
public class ElectricPoleTypeDao extends AbstractDao<ElectricPoleType, Long> {

    public static final String TABLENAME = "ELECTRIC_POLE_TYPE";

    /**
     * Properties of entity ElectricPoleType.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property MaterialNum = new Property(1, String.class, "materialNum", false, "MATERIAL_NUM");
        public final static Property MaterialDetail = new Property(2, String.class, "materialDetail", false, "MATERIAL_DETAIL");
        public final static Property MaterialUnit = new Property(3, String.class, "materialUnit", false, "MATERIAL_UNIT");
        public final static Property MaterialNameEn = new Property(4, String.class, "materialNameEn", false, "MATERIAL_NAME_EN");
        public final static Property MaterialType = new Property(5, String.class, "materialType", false, "MATERIAL_TYPE");
        public final static Property MaterialWeight = new Property(6, String.class, "materialWeight", false, "MATERIAL_WEIGHT");
        public final static Property MaterialDrawing = new Property(7, String.class, "materialDrawing", false, "MATERIAL_DRAWING");
        public final static Property MaterialTechnical = new Property(8, String.class, "materialTechnical", false, "MATERIAL_TECHNICAL");
        public final static Property Scbz = new Property(9, String.class, "scbz", false, "SCBZ");
        public final static Property Cjsj = new Property(10, java.util.Date.class, "cjsj", false, "CJSJ");
        public final static Property UpdateDate = new Property(11, java.util.Date.class, "updateDate", false, "UPDATE_DATE");
        public final static Property AreaType = new Property(12, Integer.class, "areaType", false, "AREA_TYPE");
        public final static Property AreaId = new Property(13, Integer.class, "areaId", false, "AREA_ID");
    };


    public ElectricPoleTypeDao(DaoConfig config) {
        super(config);
    }
    
    public ElectricPoleTypeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"ELECTRIC_POLE_TYPE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"MATERIAL_NUM\" TEXT," + // 1: materialNum
                "\"MATERIAL_DETAIL\" TEXT," + // 2: materialDetail
                "\"MATERIAL_UNIT\" TEXT," + // 3: materialUnit
                "\"MATERIAL_NAME_EN\" TEXT," + // 4: materialNameEn
                "\"MATERIAL_TYPE\" TEXT," + // 5: materialType
                "\"MATERIAL_WEIGHT\" TEXT," + // 6: materialWeight
                "\"MATERIAL_DRAWING\" TEXT," + // 7: materialDrawing
                "\"MATERIAL_TECHNICAL\" TEXT," + // 8: materialTechnical
                "\"SCBZ\" TEXT," + // 9: scbz
                "\"CJSJ\" INTEGER," + // 10: cjsj
                "\"UPDATE_DATE\" INTEGER," + // 11: updateDate
                "\"AREA_TYPE\" INTEGER," + // 12: areaType
                "\"AREA_ID\" INTEGER);"); // 13: areaId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_ELECTRIC_POLE_TYPE__id ON ELECTRIC_POLE_TYPE" +
                " (\"_id\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"ELECTRIC_POLE_TYPE\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ElectricPoleType entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String materialNum = entity.getMaterialNum();
        if (materialNum != null) {
            stmt.bindString(2, materialNum);
        }
 
        String materialDetail = entity.getMaterialDetail();
        if (materialDetail != null) {
            stmt.bindString(3, materialDetail);
        }
 
        String materialUnit = entity.getMaterialUnit();
        if (materialUnit != null) {
            stmt.bindString(4, materialUnit);
        }
 
        String materialNameEn = entity.getMaterialNameEn();
        if (materialNameEn != null) {
            stmt.bindString(5, materialNameEn);
        }
 
        String materialType = entity.getMaterialType();
        if (materialType != null) {
            stmt.bindString(6, materialType);
        }
 
        String materialWeight = entity.getMaterialWeight();
        if (materialWeight != null) {
            stmt.bindString(7, materialWeight);
        }
 
        String materialDrawing = entity.getMaterialDrawing();
        if (materialDrawing != null) {
            stmt.bindString(8, materialDrawing);
        }
 
        String materialTechnical = entity.getMaterialTechnical();
        if (materialTechnical != null) {
            stmt.bindString(9, materialTechnical);
        }
 
        String scbz = entity.getScbz();
        if (scbz != null) {
            stmt.bindString(10, scbz);
        }
 
        java.util.Date cjsj = entity.getCjsj();
        if (cjsj != null) {
            stmt.bindLong(11, cjsj.getTime());
        }
 
        java.util.Date updateDate = entity.getUpdateDate();
        if (updateDate != null) {
            stmt.bindLong(12, updateDate.getTime());
        }
 
        Integer areaType = entity.getAreaType();
        if (areaType != null) {
            stmt.bindLong(13, areaType);
        }
 
        Integer areaId = entity.getAreaId();
        if (areaId != null) {
            stmt.bindLong(14, areaId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ElectricPoleType readEntity(Cursor cursor, int offset) {
        ElectricPoleType entity = new ElectricPoleType( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // materialNum
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // materialDetail
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // materialUnit
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // materialNameEn
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // materialType
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // materialWeight
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // materialDrawing
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // materialTechnical
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // scbz
            cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)), // cjsj
            cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)), // updateDate
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // areaType
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13) // areaId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ElectricPoleType entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setMaterialNum(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMaterialDetail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMaterialUnit(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMaterialNameEn(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMaterialType(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMaterialWeight(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setMaterialDrawing(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setMaterialTechnical(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setScbz(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCjsj(cursor.isNull(offset + 10) ? null : new java.util.Date(cursor.getLong(offset + 10)));
        entity.setUpdateDate(cursor.isNull(offset + 11) ? null : new java.util.Date(cursor.getLong(offset + 11)));
        entity.setAreaType(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setAreaId(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ElectricPoleType entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ElectricPoleType entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
