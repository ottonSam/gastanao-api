import { v4 as uuidV4 } from "uuid";
import { Column, CreateDateColumn, Entity, OneToMany, PrimaryColumn, UpdateDateColumn } from "typeorm";
import { Category } from "../../categories/entities/Category";

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

    @OneToMany(() => Category, (category) => category.id)
    categories?: Category[];

    @CreateDateColumn()
    created_at?: Date;

    @UpdateDateColumn()
    updated_at?: Date;

    @CreateDateColumn()
    excluded_at?: Date;

    constructor() {
        if (!this.id) {
            this.id = uuidV4();
        }
        this.name = "";
        this.password = "";
        this.email = "";
        this.active = true;
    }
}

export { User };