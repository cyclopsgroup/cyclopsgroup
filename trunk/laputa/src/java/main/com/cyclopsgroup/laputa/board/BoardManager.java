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

import com.cyclopsgroup.laputa.core.RuntimeMember;

/**
 * Facade behavior interface
 * 
 * @author <a href="mailto:jiaqi.guo@gmail.com">Jiaqi Guo </a>
 */
public interface BoardManager
{
    /** Role name in context */
    String ROLE = BoardManager.class.getName();

    Board getBoard(String boardName) throws Exception;

    Board[] getBoards(BoardPackage[] parentPackage) throws Exception;

    BoardPackage getPackage(String packageName) throws Exception;

    BoardPackage[] getPackages(BoardPackage parentPackage) throws Exception;

    Post getPosts(Post parentPostId) throws Exception;

    Board newBoard(BoardPackage parentPackage, String boardName,
            RuntimeMember owner);

    BoardPackage newPackage(BoardPackage parentPackage, String packageName);

    Post newPost(Topic topic, String subject, RuntimeMember author)
            throws Exception;

    Post newTopic(Board board, String subject, RuntimeMember owner)
            throws Exception;
}
