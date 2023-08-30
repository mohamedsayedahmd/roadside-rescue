import React from "react";

import "./../App.css";
import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  actionAddTodo,
  actionRemoveTodo,
} from "./../Services/actions/TodoAction";
function TestComponent() {
  const [todo, setTodo] = useState();
  const Todo = useSelector((state) => state.todoRed.todos);
  // const {todos} = Todo; // no need cuz we got that .
  console.log(Todo);
  const dispatch = useDispatch();

  const handleSubmit = (e) => {
    e.preventDefault();
    dispatch(actionAddTodo(todo));
  };
  const handelRemove = (t) => {
    dispatch(actionRemoveTodo(t));
  };

  return (
    <div className="App">
      <header className="App-header">
        <h2>Todo List App in Redux</h2>
        <form onSubmit={handleSubmit}>
          <input
            onChange={(e) => setTodo(e.target.value)}
            placeholder="Enter a Todo"
            style={{
              width: 350,
              padding: 10,
              borderRadius: 20,
              border: "none",
              fontSize: 20,
            }}
          />
          <button
            type="submit"
            style={{
              padding: 12,
              borderRadius: 25,
              fontSize: 15,
              marginLeft: 20,
            }}
          >
            Go
          </button>
        </form>
        <ul className="allTodos">
          {Todo &&
            Todo.map((t) => {
              console.log("shit");
              return (
                <li key={t.id} className="singalTodo">
                  <span className="todoText">{t.id}</span>
                  <button
                    style={{
                      borderRadius: 25,
                      padding: 10,
                      border: "1px solid white",
                      backgroundColor: "orangered",
                    }}
                    onClick={() => handelRemove(t)}
                  >
                    Delete
                  </button>
                </li>
              );
            })}
        </ul>
      </header>
    </div>
  );
}

export default TestComponent;
