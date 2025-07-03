import React from "react";
import Login from "./pages/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import About from "./pages/About";
import Signup from "./pages/Signup";
import WeeklyMenu from "./pages/WeeklyMenu";
import ProfilePage from "./pages/ProfilePage";
import Recipes from "./pages/Recipes";
import Settings from "./pages/Settings";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/" element={<About />}></Route>
          <Route path="/signup" element={<Signup />}></Route>
          <Route path="/menu" element={<WeeklyMenu />}></Route>
          <Route path="/profile" element={<ProfilePage />}></Route>
          <Route path="/recipes" element={<Recipes />}></Route>
          <Route path="/settings" element={<Settings />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
