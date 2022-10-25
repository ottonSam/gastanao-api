import { inject, injectable } from "tsyringe";
import { AppError } from "../../../../errors/AppError";

import { IUsersRepository } from "../../repositories/IUsersRepository";

interface IRequest {
    user_id: string;
    new_name: string;
}

@injectable()
class UpdateUserNameUseCase{
    constructor(
        @inject("UsersRepository")
        private usersRepository: IUsersRepository
    ) { }

    async execute({ user_id, new_name }: IRequest): Promise<void> {
        const user = await this.usersRepository.findById(user_id);

        if (!user) {
            throw new AppError("User not found", 404);
        }

        if (new_name == "") {
            throw new AppError("Invalid name", 400);
        }

        user.name = new_name;

        await this.usersRepository.create(user);
    }
}

export { UpdateUserNameUseCase };