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
 * extended all references should be to Group
 */
public abstract class BaseGroup extends BaseObject
{
    /** The Peer class */
    private static final GroupPeer peer =
        new GroupPeer();

                  
        /**
         * The value for the group_id field
         */
        private int group_id;
              
        /**
         * The value for the group_name field
         */
        private String group_name;
              
        /**
         * The value for the description field
         */
        private String description;
                                                                                                              
        /**
         * The value for the is_system field
         */
        private boolean is_system = false;
                                                                                                              
        /**
         * The value for the is_disabled field
         */
        private boolean is_disabled = false;
      
      
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
         * Get the GroupName
         *
         * @return String
         */
        public String getGroupName()
        {
            return group_name;
        }

                
        /**
         * Set the value of GroupName
         *
         * @param v new value
         */
        public void setGroupName(String v) 
        {
          


         if (!ObjectUtils.equals(this.group_name, v))
        {
             this.group_name = v;
            setModified(true);
        }

             }


        /**
         * Get the Description
         *
         * @return String
         */
        public String getDescription()
        {
            return description;
        }

                
        /**
         * Set the value of Description
         *
         * @param v new value
         */
        public void setDescription(String v) 
        {
          


         if (!ObjectUtils.equals(this.description, v))
        {
             this.description = v;
            setModified(true);
        }

             }


        /**
         * Get the IsSystem
         *
         * @return boolean
         */
        public boolean getIsSystem()
        {
            return is_system;
        }

                
        /**
         * Set the value of IsSystem
         *
         * @param v new value
         */
        public void setIsSystem(boolean v) 
        {
          


         if (this.is_system != v)
        {
             this.is_system = v;
            setModified(true);
        }

             }


        /**
         * Get the IsDisabled
         *
         * @return boolean
         */
        public boolean getIsDisabled()
        {
            return is_disabled;
        }

                
        /**
         * Set the value of IsDisabled
         *
         * @param v new value
         */
        public void setIsDisabled(boolean v) 
        {
          


         if (this.is_disabled != v)
        {
             this.is_disabled = v;
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
                GroupPeer.doInsert((Group) this);
                setNew(false);
            }
            else
            {
                GroupPeer.doUpdate((Group) this);
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
                    GroupPeer
                        .doInsert((Group) this, con);
                    setNew(false);
                }
                else
                {
                    GroupPeer
                        .doUpdate((Group) this, con);
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
                GroupPeer.doInsert((Group) this, con);
                setNew(false);
            }
            else
            {
                GroupPeer.doUpdate((Group) this, con);
            }
        }

       }


    
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  group_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setGroupId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setGroupId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getGroupId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     */
    public Group copy() throws TorqueException
    {
        return copyInto(new Group());
    }

    protected Group copyInto(Group copyObj) throws TorqueException
    {
        copyObj.setGroupId(group_id);
        copyObj.setGroupName(group_name);
        copyObj.setDescription(description);
        copyObj.setIsSystem(is_system);
        copyObj.setIsDisabled(is_disabled);

                      copyObj.setGroupId(0);
                            


        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public GroupPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("Group:\n");
              str.append("GroupId = ")
           .append(getGroupId())
           .append("\n");
              str.append("GroupName = ")
           .append(getGroupName())
           .append("\n");
              str.append("Description = ")
           .append(getDescription())
           .append("\n");
              str.append("IsSystem = ")
           .append(getIsSystem())
           .append("\n");
              str.append("IsDisabled = ")
           .append(getIsDisabled())
           .append("\n");
              return(str.toString());
    }
}
