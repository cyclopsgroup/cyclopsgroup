
# -----------------------------------------------------------------------
# c_hf_standards
# -----------------------------------------------------------------------
drop table if exists c_hf_standards;

CREATE TABLE c_hf_standards
(
		            standard_id INTEGER NOT NULL,
		            height_from INTEGER NOT NULL,
		            height_to INTEGER NOT NULL,
		            weight_from INTEGER NOT NULL,
		            weight_to INTEGER NOT NULL,
		            age_from INTEGER NOT NULL,
		            age_to INTEGER NOT NULL,
		            is_female BIT default 0 NOT NULL,
		            daily_calorie INTEGER NOT NULL,
    PRIMARY KEY(standard_id)
);

# -----------------------------------------------------------------------
# c_hf_userprofiles
# -----------------------------------------------------------------------
drop table if exists c_hf_userprofiles;

CREATE TABLE c_hf_userprofiles
(
		            profile_id INTEGER NOT NULL,
		            user_id INTEGER NOT NULL,
		            birth_year INTEGER NOT NULL,
		            weight INTEGER NOT NULL,
		            height INTEGER NOT NULL,
		            is_female BIT default 0 NOT NULL,
    PRIMARY KEY(profile_id)
);
  
  
