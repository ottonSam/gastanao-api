import { v4 as uuidV4 } from "uuid";
import { Column, CreateDateColumn, Entity, ManyToOne, PrimaryColumn, UpdateDateColumn } from "typeorm";
import { User } from "../../accounts/entities/User";

@Entity("categories")
class Category {
    @PrimaryColumn()
    id?: string;

    @Column()
    name: string;

    @Column()
    type: string;

    @ManyToOne(() => User, (user) => user.categories)
    user_id = User

    @CreateDateColumn()
    created_at?: Date;

    @UpdateDateColumn()
    updated_at?: Date;

    @CreateDateColumn()
    excluded_at?: Date;

    constructor() {
        if(!this.id){
            this.id = uuidV4();
        }

        this.name = "";
        this.type = "";
    }
}

export { Category };