CREATE TABLE transactions (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id VARCHAR(255),
    category_id UUID NOT NULL,
    amount NUMERIC(19, 2),
    description VARCHAR(255),
    date DATE,
    type VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);