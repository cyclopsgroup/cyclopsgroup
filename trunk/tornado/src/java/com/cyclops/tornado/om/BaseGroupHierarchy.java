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
 * extended all references should be to GroupHierarchy
 */
public abstract class BaseGroupHierarchy extends BaseObject
{
    /** The Peer class */
    private static final GroupHierarchyPeer peer =
        new GroupHierarchyPeer();

                  
        /**
         * The value for the hierarchy_id field
         */
        private int hierarchy_id;
              
        /**
         * The value for the group_id field
         */
        private int group_id;
              
        /**
         * The value for the parent_group_id field
         */
        private int parent_group_id;
      
      
        /**
         * Get the HierarchyId
         *
         * @return int
         */
        public int getHierarchyId()
        {
            return hierarchy_id;
        }

                
        /**
         * Set the value of HierarchyId
         *
         * @param v new value
         */
        public void setHierarchyId(int v) 
        {
          


         if (this.hierarchy_id != v)
        {
             this.hierarchy_id = v;
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
         * Get the ParentGroupId
         *
         * @return int
         */
        public int getParentGroupId()
        {
            return parent_group_id;
        }

                
        /**
         * Set the value of ParentGroupId
         *
         * @param v new value
         */
        public void setParentGroupId(int v) 
        {
          


         if (this.parent_group_id != v)
        {
             this.parent_group_id = v;
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
                GroupHierarchyPeer.doInsert((GroupHierarchy) this);
                setNew(false);
            }
            else
            {
                GroupHierarchyPeer.doUpdate((GroupHierarchy) this);
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
                    GroupHierarchyPeer
                        .doInsert((GroupHierarchy) this, con);
                    setNew(false);
                }
                else
                {
                    GroupHierarchyPeer
                        .doUpdate((GroupHierarchy) this, con);
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
                GroupHierarchyPeer.doInsert((GroupHierarchy) this, con);
                setNew(false);
            }
            else
            {
                GroupHierarchyPeer.doUpdate((GroupHierarchy) this, con);
            }
        }

       }


    
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  hierarchy_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setHierarchyId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setHierarchyId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getHierarchyId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     */
    public GroupHierarchy copy() throws TorqueException
    {
        return copyInto(new GroupHierarchy());
    }

    protected GroupHierarchy copyInto(GroupHierarchy copyObj) throws TorqueException
    {
        copyObj.setHierarchyId(hierarchy_id);
        copyObj.setGroupId(group_id);
        copyObj.setParentGroupId(parent_group_id);

                      copyObj.setHierarchyId(0);
                    


        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public GroupHierarchyPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("GroupHierarchy:\n");
              str.append("HierarchyId = ")
           .append(getHierarchyId())
           .append("\n");
              str.append("GroupId = ")
           .append(getGroupId())
           .append("\n");
              str.append("ParentGroupId = ")
           .append(getParentGroupId())
           .append("\n");
              return(str.toString());
    }
}
