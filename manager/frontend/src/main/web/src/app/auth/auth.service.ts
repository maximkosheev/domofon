import { Injectable } from '@angular/core';

@Injectable()
export class AuthService {

    /**
     * Возвращает true, если пользователь прошел аутентификацию
     * @returns true/false
     */
    public isAuthenticated(): boolean {
        return true;
    }
}