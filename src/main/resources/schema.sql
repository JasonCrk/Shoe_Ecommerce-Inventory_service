CREATE TABLE IF NOT EXISTS categories (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    gender VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS shoe_models (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    description TEXT NOT NULL,
    category_id BINARY(16) NOT NULL,
    brand_id BINARY(16) NOT NULL,
    is_discontinued TINYINT(1) NOT NULL DEFAULT 0,
    is_published TINYINT(1) NOT NULL DEFAULT 0,
    date_published DATE
);

CREATE TABLE IF NOT EXISTS shoe_variants (
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(160) NOT NULL,
    price DECIMAL(8,2) NOT NULL,
    is_discontinued TINYINT(1) NOT NULL DEFAULT 0,
    brand_id BINARY(16) NOT NULL,
    shoe_model_id BINARY(16) NOT NULL,
    FOREIGN KEY (shoe_model_id) REFERENCES shoe_models(id)
);

CREATE TABLE IF NOT EXISTS shoe_variant_assets (
    id BINARY(16) NOT NULL PRIMARY KEY,
    position TINYINT NOT NULL,
    shoe_variant_id BINARY(16) NOT NULL,
    url TINYTEXT NOT NULL,
    FOREIGN KEY (shoe_variant_id) REFERENCES shoe_variants(id)
);

CREATE TABLE IF NOT EXISTS shoe_inventories (
    id BINARY(16) NOT NULL PRIMARY KEY,
    size DECIMAL(3,1) NOT NULL,
    stock MEDIUMINT UNSIGNED NOT NULL,
    shoe_variant_id BINARY(16) NOT NULL,
    FOREIGN KEY (shoe_variant_id) REFERENCES shoe_variants(id)
);