import { DayPicker } from "react-day-picker";
import "react-day-picker/style.css";

function Calendar() {
  return (
    <div className="flex flex-col items-center gap-4 p-4">
      <DayPicker animate mode="single" />
    </div>
  );
}

export default Calendar;
