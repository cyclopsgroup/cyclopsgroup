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
 * extended all references should be to User
 */
public abstract class BaseUser extends BaseObject
{
    /** The Peer class */
    private static final UserPeer peer =
        new UserPeer();

                  
        /**
         * The value for the user_id field
         */
        private int user_id;
              
        /**
         * The value for the user_name field
         */
        private String user_name;
              
        /**
         * The value for the encrypted_password field
         */
        private String encrypted_password;
              
        /**
         * The value for the description field
         */
        private String description;
                                                                                                              
        /**
         * The value for the is_system field
         */
        private boolean is_system = false;
              
        /**
         * The value for the email field
         */
        private String email;
              
        /**
         * The value for the first_name field
         */
        private String first_name;
              
        /**
         * The value for the middle_name field
         */
        private String middle_name;
              
        /**
         * The value for the last_name field
         */
        private String last_name;
              
        /**
         * The value for the last_signin field
         */
        private long last_signin;
                                                                            
        /**
         * The value for the signin_counter field
         */
        private int signin_counter = 0;
              
        /**
         * The value for the last_access field
         */
        private long last_access;
              
        /**
         * The value for the created_time field
         */
        private long created_time;
                                                                                                              
        /**
         * The value for the is_disabled field
         */
        private boolean is_disabled = false;
      
      
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
         * Get the UserName
         *
         * @return String
         */
        public String getUserName()
        {
            return user_name;
        }

                
        /**
         * Set the value of UserName
         *
         * @param v new value
         */
        public void setUserName(String v) 
        {
          


         if (!ObjectUtils.equals(this.user_name, v))
        {
             this.user_name = v;
            setModified(true);
        }

             }


        /**
         * Get the EncryptedPassword
         *
         * @return String
         */
        public String getEncryptedPassword()
        {
            return encrypted_password;
        }

                
        /**
         * Set the value of EncryptedPassword
         *
         * @param v new value
         */
        public void setEncryptedPassword(String v) 
        {
          


         if (!ObjectUtils.equals(this.encrypted_password, v))
        {
             this.encrypted_password = v;
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
         * Get the Email
         *
         * @return String
         */
        public String getEmail()
        {
            return email;
        }

                
        /**
         * Set the value of Email
         *
         * @param v new value
         */
        public void setEmail(String v) 
        {
          


         if (!ObjectUtils.equals(this.email, v))
        {
             this.email = v;
            setModified(true);
        }

             }


        /**
         * Get the FirstName
         *
         * @return String
         */
        public String getFirstName()
        {
            return first_name;
        }

                
        /**
         * Set the value of FirstName
         *
         * @param v new value
         */
        public void setFirstName(String v) 
        {
          


         if (!ObjectUtils.equals(this.first_name, v))
        {
             this.first_name = v;
            setModified(true);
        }

             }


        /**
         * Get the MiddleName
         *
         * @return String
         */
        public String getMiddleName()
        {
            return middle_name;
        }

                
        /**
         * Set the value of MiddleName
         *
         * @param v new value
         */
        public void setMiddleName(String v) 
        {
          


         if (!ObjectUtils.equals(this.middle_name, v))
        {
             this.middle_name = v;
            setModified(true);
        }

             }


        /**
         * Get the LastName
         *
         * @return String
         */
        public String getLastName()
        {
            return last_name;
        }

                
        /**
         * Set the value of LastName
         *
         * @param v new value
         */
        public void setLastName(String v) 
        {
          


         if (!ObjectUtils.equals(this.last_name, v))
        {
             this.last_name = v;
            setModified(true);
        }

             }


        /**
         * Get the LastSignin
         *
         * @return long
         */
        public long getLastSignin()
        {
            return last_signin;
        }

                
        /**
         * Set the value of LastSignin
         *
         * @param v new value
         */
        public void setLastSignin(long v) 
        {
          


         if (this.last_signin != v)
        {
             this.last_signin = v;
            setModified(true);
        }

             }


        /**
         * Get the SigninCounter
         *
         * @return int
         */
        public int getSigninCounter()
        {
            return signin_counter;
        }

                
        /**
         * Set the value of SigninCounter
         *
         * @param v new value
         */
        public void setSigninCounter(int v) 
        {
          


         if (this.signin_counter != v)
        {
             this.signin_counter = v;
            setModified(true);
        }

             }


        /**
         * Get the LastAccess
         *
         * @return long
         */
        public long getLastAccess()
        {
            return last_access;
        }

                
        /**
         * Set the value of LastAccess
         *
         * @param v new value
         */
        public void setLastAccess(long v) 
        {
          


         if (this.last_access != v)
        {
             this.last_access = v;
            setModified(true);
        }

             }


        /**
         * Get the CreatedTime
         *
         * @return long
         */
        public long getCreatedTime()
        {
            return created_time;
        }

                
        /**
         * Set the value of CreatedTime
         *
         * @param v new value
         */
        public void setCreatedTime(long v) 
        {
          


         if (this.created_time != v)
        {
             this.created_time = v;
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
                UserPeer.doInsert((User) this);
                setNew(false);
            }
            else
            {
                UserPeer.doUpdate((User) this);
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
                    UserPeer
                        .doInsert((User) this, con);
                    setNew(false);
                }
                else
                {
                    UserPeer
                        .doUpdate((User) this, con);
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
                UserPeer.doInsert((User) this, con);
                setNew(false);
            }
            else
            {
                UserPeer.doUpdate((User) this, con);
            }
        }

       }


    
    
    

        /**
     * Set the PrimaryKey using ObjectKey.
     *
     * @param  user_id ObjectKey
     */
    public void setPrimaryKey(ObjectKey key)
        
    {
                    setUserId(((NumberKey) key).intValue());
            }

    /**
     * Set the PrimaryKey using a String.
     *
     * @param key
     */
    public void setPrimaryKey(String key) 
    {
                    setUserId(Integer.parseInt(key));
            }


    /**
     * returns an id that differentiates this object from others
     * of its class.
     */
    public ObjectKey getPrimaryKey()
    {
        return SimpleKey.keyFor(getUserId());
    }

 

    /**
     * Makes a copy of this object.
     * It creates a new object filling in the simple attributes.
     */
    public User copy() throws TorqueException
    {
        return copyInto(new User());
    }

    protected User copyInto(User copyObj) throws TorqueException
    {
        copyObj.setUserId(user_id);
        copyObj.setUserName(user_name);
        copyObj.setEncryptedPassword(encrypted_password);
        copyObj.setDescription(description);
        copyObj.setIsSystem(is_system);
        copyObj.setEmail(email);
        copyObj.setFirstName(first_name);
        copyObj.setMiddleName(middle_name);
        copyObj.setLastName(last_name);
        copyObj.setLastSignin(last_signin);
        copyObj.setSigninCounter(signin_counter);
        copyObj.setLastAccess(last_access);
        copyObj.setCreatedTime(created_time);
        copyObj.setIsDisabled(is_disabled);

                      copyObj.setUserId(0);
                                                                


        return copyObj;
    }

    /**
     * returns a peer instance associated with this om.  Since Peer classes
     * are not to have any instance attributes, this method returns the
     * same instance for all member of this class. The method could therefore
     * be static, but this would prevent one from overriding the behavior.
     */
    public UserPeer getPeer()
    {
        return peer;
    }

    public String toString()
    {
        StringBuffer str = new StringBuffer();
        str.append("User:\n");
              str.append("UserId = ")
           .append(getUserId())
           .append("\n");
              str.append("UserName = ")
           .append(getUserName())
           .append("\n");
              str.append("EncryptedPassword = ")
           .append(getEncryptedPassword())
           .append("\n");
              str.append("Description = ")
           .append(getDescription())
           .append("\n");
              str.append("IsSystem = ")
           .append(getIsSystem())
           .append("\n");
              str.append("Email = ")
           .append(getEmail())
           .append("\n");
              str.append("FirstName = ")
           .append(getFirstName())
           .append("\n");
              str.append("MiddleName = ")
           .append(getMiddleName())
           .append("\n");
              str.append("LastName = ")
           .append(getLastName())
           .append("\n");
              str.append("LastSignin = ")
           .append(getLastSignin())
           .append("\n");
              str.append("SigninCounter = ")
           .append(getSigninCounter())
           .append("\n");
              str.append("LastAccess = ")
           .append(getLastAccess())
           .append("\n");
              str.append("CreatedTime = ")
           .append(getCreatedTime())
           .append("\n");
              str.append("IsDisabled = ")
           .append(getIsDisabled())
           .append("\n");
              return(str.toString());
    }
}
