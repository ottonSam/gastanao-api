import { v4 as uuidV4 } from "uuid";
import { Column, CreateDateColumn, Entity, PrimaryColumn } from "typeorm";

@Entity("users")
class User {
    @PrimaryColumn()
    id?: string;

    @Column()
    name: string;

    @Column()
    email: string;

    @Column()
    password: string;

    @Column()
    active: boolean;

    @Column()
    avatar?: string;

    @CreateDateColumn()
    created_at: Date;

    @CreateDateColumn()
    updated_at: Date;

    @CreateDateColumn()
    excluded_at?: Date;

    constructor() {
        if (!this.id) {
            this.id = uuidV4();
        }
        this.name = "";
        this.password = "";
        this.email = "";
        this.active = false;
        this.created_at = new Date();
        this.updated_at = new Date();
    }
}

export { User };