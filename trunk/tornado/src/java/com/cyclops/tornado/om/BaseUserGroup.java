package com.cyclops.tornado.om;


import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.torque.Torque;
import org.apache.torque.TorqueException;
import org.apache.torque.om.BaseObject;
import org.apache.torque.om.ComboKey;
import org.apache.torque.om.DateKey;
import org.apache.torque.om.NumberKey;
import org.apache.torque.om.ObjectKey;
import org.apache.torque.om.SimpleKey;
import org.apache.torque.om.StringKey;
import org.apache.torque.om.Persistent;
import org.apache.torque.util.Criteria;
import org.apache.torque.util.Transaction;

   
      
   
/**
 * You should not use this class directly.  It should not even be
 * extended all references should be to UserGroup
 */
public abstract class BaseUserGroup extends BaseObject
{
    /** The Peer class */
    private static final UserGroupPeer peer =
        new UserGroupPeer();

                  
        /**
         * The value for the id field
         */
        private int id;
              
        /**
         * The value for the user_id field
         */
        private int user_id;
              
        /**
         * The value for the group_id field
         */
        private int group_id;
      
      
        /**
         * Get the Id
         *
         * @return int
         */
        public int getId()
        {
            return id;
        }

                
        /**
         * Set the value of Id
         *
         * @param v new value
         */
        public void setId(int v) 
        {
          


         if (this.id != v)
        {
             this.id = v;
            setModified(true);
        }

             }


        /**
         * Get the UserId
         *
         * @return int
         */
        public int getUserId()
        {
            return user_id;
        }

                
        /**
         * Set the value of UserId
         *
         * @param v new value
         */
        public void setUserId(int v) 
        {
          


         if (this.user_id != v)
        {
             this.user_id = v;
            setModified(true);
        }

             }


        /**
         * Get the GroupId
         *
         * @return int
         */
        public int getGroupId()
        {
            return group_id;
        }

                
        /**
         * Set the value of GroupId
         *
         * @param v new value
         */
        public void setGroupId(int v) 
        {
          


         if (this.group_id != v)
        {
             this.group_id = v;
            setModified(true);
        }

             }


 
    
     


    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @throws Exception
     */
    public void save() throws Exception
    {
         if (isModified())
        {
            if (isNew())
            {
                UserGroupPeer.doInsert((UserGroup) this);
                setNew(false);
            }
            else
            {
                UserGroupPeer.doUpdate((UserGroup) this);
            }
        }
     }

    /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.
     *
     * @param dbName
     * @throws TorqueException
     */
    public void save(String dbName) throws TorqueException
    {
        Connection con = null;
         if (isModified())
        {
            try
            {
                con = Torque.getConnection(dbName);
                if (isNew())
                {
                    UserGroupPeer
                        .doInsert((UserGroup) this, con);
                    setNew(false);
                }
                else
                {
                    UserGroupPeer
                        .doUpdate((UserGroup) this, con);
                }
            }
            finally
            {
                Torque.closeConnection(con);
            }
        }

     }

      /**
     * Stores the object in the database.  If the object is new,
     * it inserts it; otherwise an update is performed.  This method
     * is meant to be used as part of a transaction, otherwise use
     * the save() method and the connection details will be handled
     * internally
     *
     * @param con
     * @throws TorqueException
     */
    public void save(Connection con) throws TorqueException
    {
  
        // If this object has been modified, then save it to the database.
        if (isModified())
        {
            if (isNew())
            {
                UserGroupPeer.doInsert((UserGroup) this, con);
                setNew(false);
            }
            else
            {
                UserGroupPeer.doUpdate((UserGroup) this, con);
            }
        }

       }


    
    
        
    
        
    
    

    private final SimpleKey[] pks = new SimpleKey[3];
    private final ComboKey comboPK = new ComboKey(pks);
    /**
     * Set the PrimaryKey with an ObjectKey
     *
     * @param key
     */
    public void setPrimaryKey(ObjectKey key) throws TorqueException
    {
        SimpleKey[] keys = (SimpleKey[]) key.getValue();
        SimpleKey tmpKey = null;
                             setId(((NumberKey)keys[0]).intValue());
                                setUserId(((NumberKey)keys[1]).intValue());
                                setGroupId(((NumberKey)keys[2]).intValue());
                }

    /**
     * Set the PrimaryKey using SimpleKeys.
     *
     * @param int id
     * @param int user_id
     * @param int group_id
     */
    public void setPrimaryKey( int id, int user_id, int group_id)
        
    {
         setId(id);
         setUserId(user_id);
         setGroupId(group_id);
    }

    /**
     * Set the PrimaryKey using a String.
     */
    public void setPrimaryKey(String key) throws TorqueException
    {
        setPrimaryKey(new ComboKey(key));
    }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
            pks[0] = SimpleKey.keyFor(getId());
                    pks[1] = SimpleKey.keyFor(getUserId());
                    pks[2] = SimpleKey.keyFor(getGroupId());
                    return comboPK;
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     */
    public UserGroup copy() throws TorqueException
    {
        return copyInto(new UserGroup());
    }

    protected UserGroup copyInto(UserGroup copyObj) throws TorqueException
    {
        copyObj.setId(id);
        copyObj.setUserId(user_id);
        copyObj.setGroupId(group_id);

                      copyObj.setId(0);
                                  copyObj.setUserId(0);
                                  copyObj.setGroupId(0);
            


        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UserGroupPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("UserGroup:\n");
              str.append("Id = ")
           .append(getId())
           .append("\n");
              str.append("UserId = ")
           .append(getUserId())
           .append("\n");
              str.append("GroupId = ")
           .append(getGroupId())
           .append("\n");
              return(str.toString());
    }
}
