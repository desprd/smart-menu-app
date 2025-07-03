import React from "react";
import Header from "../components/Header";

function About() {
  return (
    <div className="min-h-screen bg-white text-[#111714] font-lexend">
      <Header pageName="About" />

      <main className="max-w-3xl mx-auto px-6 py-12">
        <h1 className="text-3xl font-bold mb-4">About Smart Menu</h1>
        <p className="text-lg leading-relaxed text-[#444]">
          <strong>Smart Menu</strong> is a tool designed to help you take better
          care of your health and eating habits. By providing a few personal
          preferences and dietary goals, our system automatically generates a
          customized weekly menu just for you.
        </p>

        <p className="text-lg leading-relaxed text-[#444] mt-4">
          Whether you're trying to lose weight, eat more balanced meals, or
          simply make better food choices, Smart Menu simplifies the process by
          giving you structure without the stress of planning.
        </p>

        <p className="text-lg leading-relaxed text-[#444] mt-4">
          We believe that healthy eating should be easy, personal, and
          consistent. That's why we built this app — to support your journey
          toward a healthier lifestyle.
        </p>

        <div className="mt-8">
          <a
            href="/login"
            className="inline-block bg-[#38e07b] text-[#111714] font-bold px-6 py-3 rounded-xl hover:opacity-90 transition"
          >
            Get Started
          </a>
        </div>
      </main>
    </div>
  );
}

export default About;
