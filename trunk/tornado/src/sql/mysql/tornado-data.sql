
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

