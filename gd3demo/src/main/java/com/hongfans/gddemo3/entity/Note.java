package com.hongfans.gddemo3.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.hongfans.gddemo3.gen.DaoSession;
import com.hongfans.gddemo3.gen.ModifyDateDao;
import com.hongfans.gddemo3.gen.NoteDao;

/**
 * Created by Administrator on 2017/3/3.
 */
@Entity(      // 表名，默认为类名
        nameInDb = "table_note"
)
public class Note {

    // 数据库主键
    @Id(autoincrement = true)
    private Long id;

    // 唯一，默认索引
    @Unique
    private Long noteId;

    // 列名
    @Property(nameInDb = "col_name")
    private String name;

    // 不持久化
    @Transient
    private int tempUsageCount;

    @ToMany(referencedJoinProperty = "fk_noteId")
    private List<ModifyDate> modifyDates;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 363862535)
    private transient NoteDao myDao;


    @Generated(hash = 1934294999)
    public Note(Long id, Long noteId, String name) {
        this.id = id;
        this.noteId = noteId;
        this.name = name;
    }

    @Generated(hash = 1272611929)
    public Note() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTempUsageCount() {
        return tempUsageCount;
    }

    public void setTempUsageCount(int tempUsageCount) {
        this.tempUsageCount = tempUsageCount;
    }

    public void setModifyDates(List<ModifyDate> modifyDates) {
        this.modifyDates = modifyDates;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", noteId=" + noteId +
                ", name='" + name + '\'' +
                ", tempUsageCount=" + tempUsageCount +
                ", modifyDates=" + getModifyDates() +
                '}' + "\n";
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1406011711)
    public List<ModifyDate> getModifyDates() {
        if (modifyDates == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ModifyDateDao targetDao = daoSession.getModifyDateDao();
            List<ModifyDate> modifyDatesNew = targetDao._queryNote_ModifyDates(id);
            synchronized (this) {
                if (modifyDates == null) {
                    modifyDates = modifyDatesNew;
                }
            }
        }
        return modifyDates;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 2142473227)
    public synchronized void resetModifyDates() {
        modifyDates = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 799086675)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getNoteDao() : null;
    }
}
