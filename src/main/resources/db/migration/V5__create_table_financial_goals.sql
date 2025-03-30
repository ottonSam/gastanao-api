CREATE TABLE financial_goals (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id VARCHAR(255),
    category_id UUID NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    target_amount NUMERIC(19, 2),
    current_amount NUMERIC(19, 2),
    target_date DATE,
    completed BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);