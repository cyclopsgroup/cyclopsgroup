
# -----------------------------------------------------------------------
# c_a4trim_categories
# -----------------------------------------------------------------------
drop table if exists c_a4trim_categories;

CREATE TABLE c_a4trim_categories
(
		            category_id INTEGER NOT NULL,
		            parent_id INTEGER default -1 NOT NULL,
		            category_path VARCHAR (255) NOT NULL,
		            category_code VARCHAR (255) NOT NULL,
		            depth INTEGER default 0 NOT NULL,
		            description MEDIUMTEXT,
		            thumbnail_uri VARCHAR (255),
		            thumbnail_available BIT default 0 NOT NULL,
    PRIMARY KEY(category_id),
    UNIQUE (category_code),
    UNIQUE (category_path)
);

# -----------------------------------------------------------------------
# c_a4trim_products
# -----------------------------------------------------------------------
drop table if exists c_a4trim_products;

CREATE TABLE c_a4trim_products
(
		            product_id INTEGER NOT NULL,
		            product_code VARCHAR (255) NOT NULL,
		            description MEDIUMTEXT,
		            thumbnail_uri VARCHAR (255),
		            thumbnail_available BIT default 0 NOT NULL,
		            image_uri VARCHAR (255),
		            image_available BIT default 0 NOT NULL,
		            attribute1 VARCHAR (255),
		            attribute2 VARCHAR (255),
		            attribute3 VARCHAR (255),
		            attribute4 VARCHAR (255),
		            attribute5 VARCHAR (255),
    PRIMARY KEY(product_id),
    UNIQUE (product_code)
);

# -----------------------------------------------------------------------
# c_a4trim_prd_cat
# -----------------------------------------------------------------------
drop table if exists c_a4trim_prd_cat;

CREATE TABLE c_a4trim_prd_cat
(
		            id INTEGER NOT NULL,
		            product_id INTEGER NOT NULL,
		            category_id INTEGER NOT NULL,
		            category_path VARCHAR (255) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (product_id) REFERENCES c_a4trim_products (product_id)
    ,
    FOREIGN KEY (category_id) REFERENCES c_a4trim_categories (category_id)
    ,
    UNIQUE (product_id, category_id)
);
  
  
  
