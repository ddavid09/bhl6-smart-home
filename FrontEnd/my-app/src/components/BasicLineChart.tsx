import React from 'react'
import '../styles/BasicLineChart.scss'
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';
import BA from "../resources/data/BA.json"

const BasicLineChart = () => (
  <LineChart width={1400} height={600} data={BA} margin={{ top: 20, right: 20, bottom: 20, left: 0 }}>
    <Line type="monotone" dataKey="Open" stroke="#8884d8" />
    <Line type="monotone" dataKey="Low" stroke="#8884d8" />
    <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
    <XAxis dataKey="Date" stroke="#8884d8" />
    <YAxis stroke="#8884d8" />
    <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
  </LineChart>
);

export default BasicLineChart