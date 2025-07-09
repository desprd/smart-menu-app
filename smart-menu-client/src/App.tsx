import React from "react";
import Login from "./pages/Login";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import About from "./pages/About";
import Signup from "./pages/Signup";
import WeeklyMenu from "./pages/WeeklyMenu";
import ProfilePage from "./pages/ProfilePage";
import Recipes from "./pages/Recipes";
import Settings from "./pages/Settings";
import Help from "./pages/Help";
import ForgotPassword from "./pages/ForgotPassword";
import ResetPassword from "./pages/ResetPassword";
import ProtectedRoute from "./components/ProtectedRoute";
import CreateProfilePage from "./pages/CreateProfilePage";
import CreateRecipesPage from "./pages/CreateRecipesPage";
import Recipe from "./pages/Recipe";
import TokenRoute from "./components/TokenRoute";

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/" element={<About />}></Route>
          <Route path="/signup" element={<Signup />}></Route>
          <Route
            path="/createprofile"
            element={
              <ProtectedRoute>
                <CreateProfilePage />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/createrecipes"
            element={
              <ProtectedRoute>
                <CreateRecipesPage />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/menu"
            element={
              <ProtectedRoute>
                <WeeklyMenu />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/recipe/:id"
            element={
              <ProtectedRoute>
                <Recipe />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/profile"
            element={
              <ProtectedRoute>
                <ProfilePage />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/recipes"
            element={
              <ProtectedRoute>
                <Recipes />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/settings"
            element={
              <ProtectedRoute>
                <Settings />
              </ProtectedRoute>
            }
          ></Route>
          <Route
            path="/help"
            element={
              <ProtectedRoute>
                <Help />
              </ProtectedRoute>
            }
          ></Route>
          <Route path="/forgotpassword" element={<ForgotPassword />}></Route>
          <Route
            path="/resetpassword"
            element={
              <TokenRoute>
                <ResetPassword />
              </TokenRoute>
            }
          ></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
