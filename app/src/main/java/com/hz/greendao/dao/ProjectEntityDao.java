package com.hz.greendao.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.hz.greendao.dao.ProjectEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROJECT_ENTITY".
*/
public class ProjectEntityDao extends AbstractDao<ProjectEntity, Long> {

    public static final String TABLENAME = "PROJECT_ENTITY";

    /**
     * Properties of entity ProjectEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property ProjectName = new Property(1, String.class, "projectName", false, "PROJECT_NAME");
        public final static Property ProgrammeName = new Property(2, String.class, "programmeName", false, "PROGRAMME_NAME");
        public final static Property ProjectLeader = new Property(3, Integer.class, "projectLeader", false, "PROJECT_LEADER");
        public final static Property Cjsj = new Property(4, String.class, "cjsj", false, "CJSJ");
        public final static Property PicId = new Property(5, Integer.class, "picId", false, "PIC_ID");
        public final static Property Scbz = new Property(6, Integer.class, "scbz", false, "SCBZ");
        public final static Property ProjectCreator = new Property(7, Integer.class, "projectCreator", false, "PROJECT_CREATOR");
        public final static Property Status = new Property(8, String.class, "status", false, "STATUS");
        public final static Property ProjectNum = new Property(9, String.class, "projectNum", false, "PROJECT_NUM");
        public final static Property VoltageType = new Property(10, Integer.class, "voltageType", false, "VOLTAGE_TYPE");
        public final static Property TerrainId = new Property(11, Integer.class, "terrainId", false, "TERRAIN_ID");
        public final static Property BelongId = new Property(12, Integer.class, "belongId", false, "BELONG_ID");
        public final static Property AreaType = new Property(13, Integer.class, "areaType", false, "AREA_TYPE");
        public final static Property AreaId = new Property(14, Integer.class, "areaId", false, "AREA_ID");
    };


    public ProjectEntityDao(DaoConfig config) {
        super(config);
    }
    
    public ProjectEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROJECT_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"PROJECT_NAME\" TEXT," + // 1: projectName
                "\"PROGRAMME_NAME\" TEXT," + // 2: programmeName
                "\"PROJECT_LEADER\" INTEGER," + // 3: projectLeader
                "\"CJSJ\" TEXT," + // 4: cjsj
                "\"PIC_ID\" INTEGER," + // 5: picId
                "\"SCBZ\" INTEGER," + // 6: scbz
                "\"PROJECT_CREATOR\" INTEGER," + // 7: projectCreator
                "\"STATUS\" TEXT," + // 8: status
                "\"PROJECT_NUM\" TEXT," + // 9: projectNum
                "\"VOLTAGE_TYPE\" INTEGER," + // 10: voltageType
                "\"TERRAIN_ID\" INTEGER," + // 11: terrainId
                "\"BELONG_ID\" INTEGER," + // 12: belongId
                "\"AREA_TYPE\" INTEGER," + // 13: areaType
                "\"AREA_ID\" INTEGER);"); // 14: areaId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_PROJECT_ENTITY__id ON PROJECT_ENTITY" +
                " (\"_id\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROJECT_ENTITY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ProjectEntity entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String projectName = entity.getProjectName();
        if (projectName != null) {
            stmt.bindString(2, projectName);
        }
 
        String programmeName = entity.getProgrammeName();
        if (programmeName != null) {
            stmt.bindString(3, programmeName);
        }
 
        Integer projectLeader = entity.getProjectLeader();
        if (projectLeader != null) {
            stmt.bindLong(4, projectLeader);
        }
 
        String cjsj = entity.getCjsj();
        if (cjsj != null) {
            stmt.bindString(5, cjsj);
        }
 
        Integer picId = entity.getPicId();
        if (picId != null) {
            stmt.bindLong(6, picId);
        }
 
        Integer scbz = entity.getScbz();
        if (scbz != null) {
            stmt.bindLong(7, scbz);
        }
 
        Integer projectCreator = entity.getProjectCreator();
        if (projectCreator != null) {
            stmt.bindLong(8, projectCreator);
        }
 
        String status = entity.getStatus();
        if (status != null) {
            stmt.bindString(9, status);
        }
 
        String projectNum = entity.getProjectNum();
        if (projectNum != null) {
            stmt.bindString(10, projectNum);
        }
 
        Integer voltageType = entity.getVoltageType();
        if (voltageType != null) {
            stmt.bindLong(11, voltageType);
        }
 
        Integer terrainId = entity.getTerrainId();
        if (terrainId != null) {
            stmt.bindLong(12, terrainId);
        }
 
        Integer belongId = entity.getBelongId();
        if (belongId != null) {
            stmt.bindLong(13, belongId);
        }
 
        Integer areaType = entity.getAreaType();
        if (areaType != null) {
            stmt.bindLong(14, areaType);
        }
 
        Integer areaId = entity.getAreaId();
        if (areaId != null) {
            stmt.bindLong(15, areaId);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ProjectEntity readEntity(Cursor cursor, int offset) {
        ProjectEntity entity = new ProjectEntity( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // projectName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // programmeName
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // projectLeader
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // cjsj
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // picId
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // scbz
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // projectCreator
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // status
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // projectNum
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // voltageType
            cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11), // terrainId
            cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12), // belongId
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // areaType
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14) // areaId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ProjectEntity entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setProjectName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProgrammeName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setProjectLeader(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setCjsj(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPicId(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setScbz(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setProjectCreator(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setStatus(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setProjectNum(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setVoltageType(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setTerrainId(cursor.isNull(offset + 11) ? null : cursor.getInt(offset + 11));
        entity.setBelongId(cursor.isNull(offset + 12) ? null : cursor.getInt(offset + 12));
        entity.setAreaType(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setAreaId(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ProjectEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ProjectEntity entity) {
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
