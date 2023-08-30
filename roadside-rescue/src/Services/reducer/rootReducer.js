import { combineReducers } from "redux";
import { todoReducer } from "./todoReducer";
import { loginReducer } from "./loginReducer";
export const rootReducer = combineReducers({
  todoRed: todoReducer,
  loginRed: loginReducer,
});
