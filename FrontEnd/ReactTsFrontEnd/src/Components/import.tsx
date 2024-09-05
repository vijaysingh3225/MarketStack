import React, { useState, ChangeEvent, MouseEvent } from "react";
import Papa from "papaparse";

interface CsvData {
    [key: string]: any;
}

// Define the target format for TradeExec
interface TradeExec {
    account: string;
    tradeDate: string; // ISO format for LocalDate
    settlementDate: string; // ISO format for LocalDate
    currency: string;
    type: number;
    side: string;
    symbol: string;
    quantity: number;
    price: number;
    execTime: string; // ISO format for LocalTime
    commission: number;
    secFee: number;
    taf: number;
    nscc: number;
    nasdaq: number;
    ecnRemove: number;
    ecnAdd: number;
    grossProceeds: number;
    netProceeds: number;
    clearingBroker: string;
    liquidity: string;
    note: string;
}

const NEW_HEADERS = [
    "account", "tradeDate", "settlementDate", "currency", "type", "side", 
    "symbol", "quantity", "price", "execTime", "commission", "secFee", 
    "taf", "nscc", "nasdaq", "ecnRemove", "ecnAdd", "grossProceeds", 
    "netProceeds", "clearingBroker", "liquidity", "note"
];

function ImportButton() {
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const [jsonData, setJsonData] = useState<TradeExec[]>([]);

    // Handle file input change
    const handleFileChange = (event: ChangeEvent<HTMLInputElement>) => {
        const file = event.target.files ? event.target.files[0] : null;
        setSelectedFile(file);
    };

    // Handle file import
    const handleImport = (event: MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        if (!selectedFile) {
            alert("Please select a file first.");
            return;
        }

        // Read the CSV file
        const reader = new FileReader();
        reader.onload = () => {
            // Ensure csvData is a string
            const csvData = reader.result as string;

            // Convert CSV to JSON using PapaParse
            Papa.parse(csvData, {
                header: true,
                skipEmptyLines: true,
                complete: (results) => {
                    const parsedData = results.data as CsvData[];

                    // Map CSV data to TradeExec format
                    const transformedData: TradeExec[] = parsedData.map(row => ({
                        account: row["Account Number"] || "",
                        tradeDate: row["Trade Date"] ? new Date(row["Trade Date"]).toISOString().split('T')[0] : "",
                        settlementDate: row["Settlement Date"] ? new Date(row["Settlement Date"]).toISOString().split('T')[0] : "",
                        currency: row["Currency"] || "",
                        type: isNaN(Number(row["Type"])) ? 0 : Number(row["Type"]),
                        side: row["Side"] || "",
                        symbol: row["Symbol"] || "",
                        quantity: isNaN(Number(row["Quantity"])) ? 0 : Number(row["Quantity"]),
                        price: isNaN(parseFloat(row["Price"])) ? 0.0 : parseFloat(row["Price"]),
                        execTime: row["Exec Time"] || "",
                        commission: isNaN(parseFloat(row["Commission"])) ? 0.0 : parseFloat(row["Commission"]),
                        secFee: isNaN(parseFloat(row["Sec Fee"])) ? 0.0 : parseFloat(row["Sec Fee"]),
                        taf: isNaN(parseFloat(row["TAF"])) ? 0.0 : parseFloat(row["TAF"]),
                        nscc: isNaN(parseFloat(row["NSCC"])) ? 0.0 : parseFloat(row["NSCC"]),
                        nasdaq: isNaN(parseFloat(row["NASDAQ"])) ? 0.0 : parseFloat(row["NASDAQ"]),
                        ecnRemove: isNaN(parseFloat(row["ECN Remove"])) ? 0.0 : parseFloat(row["ECN Remove"]),
                        ecnAdd: isNaN(parseFloat(row["ECN Add"])) ? 0.0 : parseFloat(row["ECN Add"]),
                        grossProceeds: isNaN(parseFloat(row["Gross Proceeds"])) ? 0.0 : parseFloat(row["Gross Proceeds"]),
                        netProceeds: isNaN(parseFloat(row["Net Proceeds"])) ? 0.0 : parseFloat(row["Net Proceeds"]),
                        clearingBroker: row["Clearing Broker"] || "",
                        liquidity: row["Liquidity"] || "",
                        note: row["Note"] || "",
                    }));

                    // Store the JSON data in state
                    setJsonData(transformedData);

                    // Post JSON data to the API
                    fetch("http://localhost:8080/api/v1/openTrades", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(transformedData),
                    })
                    .then(response => response.json())
                    .then(data => {
                        console.log("Success:", data);
                    })
                    .catch(error => {
                        console.error("Error:", error);
                    });
                },
                error: (error) => {
                    console.error("Error parsing CSV:", error);
                },
            });
        };

        reader.readAsText(selectedFile);
    };

    return (
        <div>
            <input type="file" accept=".csv" onChange={handleFileChange} />
            <button type="button" onClick={handleImport}>Import</button>

            <div>
                <h3>Imported Data:</h3>
                <pre>{JSON.stringify(jsonData, null, 2)}</pre>
            </div>
        </div>
    );
}

export default ImportButton;