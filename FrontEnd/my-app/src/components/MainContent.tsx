import React from "react";
import BasicLineChart from "./BasicLineChart";
import "../styles/MainContent.scss";

function MainContent() {
  return (
    <div>
      <h1>
        <i>Zarządzanie energią w nowoczesnym domu</i>
      </h1>
      <div className="basicLineChart">
        <BasicLineChart />
      </div>
    </div>
  );
}

export default MainContent;
