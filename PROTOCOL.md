## Networking

Protocol uses TCP.

## Base Packet

All packets are wrapped in this format.

<table>
    <thead>
        <tr>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Packet ID</td>
            <td>uint8</td>
            <td>Packet indentifier.</td>
        </tr>
        <tr>
            <td>Payload length</td>
            <td>VLQ</td>
            <td>The length of the payload, in bytes.</td>
        </tr>
        <tr>
            <td>Payload</td>
            <td>Varies</td>
            <td>The data of the packet. Types vary based on packet.</td>
        </tr>
    </tbody>
</table>

## Packets

#### Heartbeat

A heartbeat packet is sent every 30 seconds from the server.  The client must respond with the exact same packet within 30 seconds otherwise they will be disconnected.  If the server doesn’t send any heartbeat packets for 30 seconds then the user will disconnect from the server with a “Timed out” exception.

<table>
    <thead>
        <tr>
            <th>Packet ID</th>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr><td rowspan="2">0x00</td></tr>
        <tr>
            <td>Heartbeat ID</td>
            <td>VLQ</td>
            <td>A randomly generated ID the that client must respond with.</td>
        </tr>
    </tbody>
</table>

#### Client Connect

<table>
    <thead>
        <tr>
            <th>Packet ID</th>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr><td rowspan="2">0x01</td></tr>
        <tr>
            <td>Name</td>
            <td>String</td>
            <td>Client's name.</td>
        </tr>
    </tbody>
</table>

#### Connection Response

<table>
    <thead>
        <tr>
            <th>Packet ID</th>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr><td rowspan="4">0x02</td></tr>
        <tr>
            <td>Success</td>
            <td>boolean</td>
            <td>Was the connection successful.</td>
        </tr>
        <tr>
            <td>Client ID</td>
            <td>VLQ</td>
            <td>Client's indentifier.</td>
        </tr>
        <tr>
            <td>Rejection reason</td>
            <td>String</td>
            <td>If failed connection, present with length of 0. Why the connection failed.</td>
        </tr>
    </tbody>
</table>

#### Chat Message

<table>
    <thead>
        <tr>
            <th>Packet ID</th>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr><td rowspan="3">0x03</td></tr>
        <tr>
            <td>Message</td>
            <td>String</td>
            <td>Client's message.</td>
        </tr>
    </tbody>
</table>

#### Client Disconnect

<table>
    <thead>
        <tr>
            <th>Packet ID</th>
            <th>Field Name</th>
            <th>Field Type</th>
            <th>Notes</th>
        </tr>
    </thead>
    <tbody>
        <tr><td rowspan="2">0x04</td></tr>
        <tr>
            <td>Reason</td>
            <td>String</td>
            <td>Reason for the disconnect.</td>
        </tr>
    </tbody>
</table>