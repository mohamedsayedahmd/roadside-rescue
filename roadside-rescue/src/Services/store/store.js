import { createStore, applyMiddleware, compose } from "redux";
import thunk from "redux-thunk";
import { rootReducer } from "./../reducer/rootReducer";
import { composeWithDevTools } from "redux-devtools-extension";

// Apply any additional middleware you might have in this array
const middlewares = [thunk];

// If you have more enhancers, add them to this array
const enhancers = [applyMiddleware(...middlewares), composeWithDevTools()];

// Combining enhancers using the compose function
const composedEnhancers = compose(...enhancers);

// Creating the store with the combined enhancers
export const store = createStore(rootReducer, composedEnhancers);
