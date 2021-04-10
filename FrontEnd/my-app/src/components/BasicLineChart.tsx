import React from 'react'
import '../styles/BasicLineChart.scss'
import { LineChart, Line, CartesianGrid, XAxis, YAxis, Tooltip } from 'recharts';
import BA from "../resources/data/2021-04-11.json"
import BB from "../resources/data/daily.json"

const BasicLineChart = () => (
  <div>
    <div className="wykresDuzy">
    <h2>Wykres roczny - Fundusz i koszty grzewcze</h2>
    <LineChart width={1400} height={600} data={BB} >
      <Line type="monotone" dataKey="earnings" stroke="#8884d8" strokeWidth={2} dot={false}/>
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="date" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
    </div>
    
    <div className="wykres">
    <h2>Wykres na dzień 11/04/2021 - Fundusz i koszty grzewcze</h2>
    <LineChart width={700} height={400} data={BA} >
      <Line type="monotone" dataKey="earnings" stroke="#8884d8" strokeWidth={2} dot={false}/>
      <Line type="monotone" dataKey="salary" stroke="#dd0204" strokeWidth={2} dot={false}/>

      <Line type="monotone" dataKey="tempIn" strokeWidth={0} dot={false} activeDot={false}/>
      <Line type="monotone" dataKey="tempOut" strokeWidth={0} dot={false} activeDot={false}/>
      <Line type="monotone" dataKey="tempExpected" strokeWidth={0} dot={false} activeDot={false}/>
      <Line type="monotone" dataKey="clouds" strokeWidth={0} dot={false} activeDot={false}/>
      <Line type="monotone" dataKey="solarSystemPower" strokeWidth={0} dot={false} activeDot={false}/>
      <Line type="monotone" dataKey="batteryCapacity" strokeWidth={0} dot={false} activeDot={false}/>

      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="time" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
    </div>

    <div className="wykres">
    <h2>Wykres na dzień 11/04/2021 - Temperatura w domu / Temperatura na zewnątrz / Temperatura oczekiwana</h2>
    <LineChart width={700} height={400} data={BA} >
      <Line type="monotone" dataKey="tempIn" stroke="#009900" strokeWidth={2} dot={false}/>
      <Line type="monotone" dataKey="tempOut" stroke="#ffff00" strokeWidth={2} dot={false}/>
      <Line type="monotone" dataKey="tempExpected" stroke="#dd0204" strokeWidth={2} dot={false}/>
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="time" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
    </div>

    <div className="wykres">
    <h2>Wykres na dzień 11/04/2021 - Zachmurzenie / Moc generowana przez system solarny</h2>
    <LineChart width={700} height={400} data={BA} >
      <Line type="monotone" dataKey="clouds" stroke="#009900" strokeWidth={2} dot={false}/>
      <Line type="monotone" dataKey="solarSystemPower" stroke="#dd0204" strokeWidth={2} dot={false}/>
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="time" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
    </div>

    <div className="wykres">
    <h2>Wykres na dzień 11/04/2021 - Poziom wody w trakcie dnia / Poziom naładowanai akumulatora</h2>
    <LineChart width={700} height={400} data={BA} >
      <Line type="monotone" dataKey="waterLevel" stroke="#009900" strokeWidth={2} dot={false}/>
      <Line type="monotone" dataKey="batteryCapacity" stroke="#dd0204" strokeWidth={2} dot={false}/>
      <CartesianGrid stroke="#ccc" strokeDasharray="5 5" />
      <XAxis dataKey="time" stroke="#8884d8" />
      <YAxis stroke="#8884d8" />
      <Tooltip wrapperStyle={{ backgroundColor: '#ccc', color: '#222' }}/>
    </LineChart>
    </div>
  </div>
);

export default BasicLineChart