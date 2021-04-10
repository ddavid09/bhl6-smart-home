import React from 'react'
import '../styles/BasicLineChart.scss'
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';
import BA from "../resources/data/2021-04-10.json"
import BB from "../resources/data/daily.json"

const BasicLineChart = () => (
  <>
    <LineChart width={1400} height={600} data={BA} margin={{ top: 20, right: 20, bottom: 20, left: 0 }}>
      <Line type="monotone" dataKey="earnings" stroke="#8884d8" />
      <Line type="monotone" dataKey="salary" stroke="#8884d8" />
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="time" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>

    <LineChart width={1400} height={600} data={BB} margin={{ top: 20, right: 20, bottom: 20, left: 0 }}>
      <Line type="monotone" dataKey="earnings" stroke="#8884d8" />
      <Line type="monotone" dataKey="salary" stroke="#8884d8" />
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="date" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
  </>
);

export default BasicLineChart