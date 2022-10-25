import { Request, Response } from "express";
import { container } from "tsyringe";

import { UpdateUserNameUseCase } from "./UpdateUserNameUseCase";

class UpdateUserNameController {
    async handle(request: Request, response: Response): Promise<Response> {
        const { id: user_id } = request.user;
        const { new_name } = request.body

        const updateUserNameUseCase = container.resolve(UpdateUserNameUseCase);
 
        await updateUserNameUseCase.execute({
            user_id,
            new_name
        });

        return response.status(204).send(); 
    }
}

export { UpdateUserNameController };