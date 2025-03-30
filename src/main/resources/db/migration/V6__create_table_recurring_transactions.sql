CREATE TABLE recurring_transactions (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id VARCHAR(255),
    category_id UUID NOT NULL,
    description VARCHAR(255),
    amount NUMERIC(19, 2),
    start_date DATE,
    end_date DATE,
    recurrence VARCHAR(255),
    day_of_month INT,
    day_of_week INT,
    FOREIGN KEY (user_id) REFERENCES users(username) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);