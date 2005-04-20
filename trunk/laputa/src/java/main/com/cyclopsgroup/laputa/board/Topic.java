/* ==========================================================================
 * Copyright 2002-2005 Cyclops Group Community
 * 
 * Licensed under the Open Software License, Version 2.1 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://opensource.org/licenses/osl-2.1.php
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * =========================================================================
 */
package com.cyclopsgroup.laputa.board;

import java.util.Date;

import com.cyclopsgroup.laputa.core.BaseEntity;

/**
 * Topic model
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public class Topic extends BaseEntity
{

    /**
     * 
     * @uml.property name="boardId" multiplicity="(0 1)"
     */
    private String boardId;

    /**
     * 
     * @uml.property name="createdTime" multiplicity="(0 1)"
     */
    private Date createdTime;

    /**
     * 
     * @uml.property name="firstPostId" multiplicity="(0 1)"
     */
    private String firstPostId;

    /**
     * 
     * @uml.property name="ownerId" multiplicity="(0 1)"
     */
    private String ownerId;

    /**
     * 
     * @uml.property name="subject" multiplicity="(0 1)"
     */
    private String subject;

    /**
     * Getter method for boardId
     * 
     * @return Returns the boardId.
     * 
     * @uml.property name="boardId"
     */
    public String getBoardId() {
        return boardId;
    }

    /**
     * Getter method for createdTime
     * 
     * @return Returns the createdTime.
     * 
     * @uml.property name="createdTime"
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * Getter method for firstPostId
     * 
     * @return Returns the firstPostId.
     * 
     * @uml.property name="firstPostId"
     */
    public String getFirstPostId() {
        return firstPostId;
    }

    /**
     * Getter method for ownerId
     * 
     * @return Returns the ownerId.
     * 
     * @uml.property name="ownerId"
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * Getter method for subject
     * 
     * @return Returns the subject.
     * 
     * @uml.property name="subject"
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Setter method for boardId
     * 
     * @param boardId The boardId to set.
     * 
     * @uml.property name="boardId"
     */
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    /**
     * Setter method for createdTime
     * 
     * @param createdTime The createdTime to set.
     * 
     * @uml.property name="createdTime"
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * Setter method for firstPostId
     * 
     * @param firstPostId The firstPostId to set.
     * 
     * @uml.property name="firstPostId"
     */
    public void setFirstPostId(String firstPostId) {
        this.firstPostId = firstPostId;
    }

    /**
     * Setter method for ownerId
     * 
     * @param ownerId The ownerId to set.
     * 
     * @uml.property name="ownerId"
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * Setter method for subject
     * 
     * @param subject The subject to set.
     * 
     * @uml.property name="subject"
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

}
