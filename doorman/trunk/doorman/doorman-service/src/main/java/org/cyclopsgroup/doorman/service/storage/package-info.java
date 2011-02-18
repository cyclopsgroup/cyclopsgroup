/**
 * JPA data POJOs
 */
@TypeDefs( { @TypeDef( name = "datetime", typeClass = DateTimeUserType.class ) } )
package org.cyclopsgroup.doorman.service.storage;

import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

