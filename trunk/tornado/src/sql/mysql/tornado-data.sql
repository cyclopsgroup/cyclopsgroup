
INSERT INTO c_tnd_users (user_id,user_name,encrypted_password,is_system,is_disabled,email,signin_counter,first_name,last_name)
    VALUES (1,'guest','Z3Vlc3Q=',1,0,'test@cyclops.org',0,'John','Clark');

INSERT INTO c_tnd_users (user_id,user_name,encrypted_password,is_system,is_disabled,email,signin_counter,first_name,last_name)
    VALUES (2,'administrator','YWRtaW5pc3RyYXRvcg==',1,0,'test@cyclops.org',0,'John','Clark');

INSERT INTO c_tnd_users (user_id,user_name,encrypted_password,is_system,is_disabled,email,signin_counter,first_name,last_name)
    VALUES (21,'jason','amFzb24=',0,0,'test@cyclops.org',0,'John','Clark');

INSERT INTO c_tnd_users (user_id,user_name,encrypted_password,is_system,is_disabled,email,signin_counter,first_name,last_name)
    VALUES (22,'john','am9obg==',1,0,'test@cyclops.org',0,'John','Clark');

INSERT INTO c_tnd_users (user_id,user_name,encrypted_password,is_system,is_disabled,email,signin_counter,first_name,last_name)
    VALUES (23,'joel','am9lbA==',1,0,'test@cyclops.org',0,'John','Clark');

INSERT INTO c_tnd_groups (group_id,group_name,is_system,is_disabled)
    VALUES (1,'administrators',1,0);

INSERT INTO c_tnd_groups (group_id,group_name,is_system,is_disabled)
    VALUES (2,'users',1,0);

INSERT INTO c_tnd_user_group (object_id,user_id,group_id)
    VALUES (1,2,1);

INSERT INTO c_tnd_user_group (object_id,user_id,group_id)
    VALUES (2,22,2);

INSERT INTO c_tnd_user_group (object_id,user_id,group_id)
    VALUES (3,23,2);

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (1,'U','guest',1,'browse');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (11,'G','administrators',1,'almighty');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (12,'G','users',1,'basic');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (21,'R','almighty',0,'screen:*.vm');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (22,'R','almighty',0,'action:*|do*');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (31,'R','browse',0,'screen:Index.vm');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (32,'R','browse',0,'screen:Redirect.vm');

INSERT INTO c_tnd_acls (acl_id,owner_type,owner_name,is_role,permission)
    VALUES (36,'R','browse',0,'action:UserIdentify|doSignin');

INSERT INTO c_tnd_roles (role_id,role_name)
    VALUES (1,'almighty');

INSERT INTO c_tnd_roles (role_id,role_name)
    VALUES (2,'basic');

INSERT INTO c_tnd_roles (role_id,role_name)
    VALUES (3,'browse');

INSERT INTO c_tnd_confs (conf_id,conf_key,conf_value)
    VALUES (11,'services.UserService.user.implementation','com.cyclops.tornado.TornadoUser');

INSERT INTO c_tnd_confs (conf_id,conf_key,conf_value)
    VALUES (12,'services.UserService.listener','com.cyclops.tornado.modules.PassportBuilder');

INSERT INTO c_tnd_confs (conf_id,conf_key,conf_value)
    VALUES (13,'services.UserService.listener','com.cyclops.tornado.modules.UserSigninCounter');

INSERT INTO c_tnd_confs (conf_id,conf_key,conf_value)
    VALUES (21,'services.NavigatorService.path','WEB-INF/conf/navigator');

INSERT INTO c_tnd_confs (conf_id,conf_key,conf_value)
    VALUES (22,'services.NavigatorService.pattern','*.xml');

