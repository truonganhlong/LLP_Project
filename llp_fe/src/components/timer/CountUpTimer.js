import moment from "moment";
import PropTypes from "prop-types";
import { useState, useEffect } from "react";

CountUpTimer.propTypes = {
	isStarted: PropTypes.bool,
	isCleared: PropTypes.bool,
	formatClock: PropTypes.string,
	storageName: PropTypes.string,
};

function CountUpTimer({
	isStarted,
	isCleared,
	formatClock,
	storageName,
}) {
	const [start, setStart] = useState(isStarted);
	const [count, setCount] = useState(0);
	const [time, setTime] = useState("00:00:00");

	var initTime = new Date();

	const showTimer = (ms) => {
		const milliseconds = Math.floor((ms % 1000) / 10)
			.toString()
			.padStart(2, "0");
		const second = Math.floor((ms / 1000) % 60)
			.toString()
			.padStart(2, "0");
		const minute = Math.floor((ms / 1000 / 60) % 60)
			.toString()
			.padStart(2, "0");
		const hour = Math.floor(ms / 1000 / 60 / 60).toString();

		setTime(
			hour.padStart(2, "0") + ":" + minute + ":" + second
			// + ":" + milliseconds
		);

		// const stringDate = moment(ms).format(formatClock || "hh:mm:ss");
		// setTime(stringDate);
	};

	const clearTime = () => {
		setTime("00:00:00");
		setCount(0);
	};

	useEffect(() => {
		clearTime();
	}, [isCleared]);

	useEffect(() => {
		if (!start) {
			return;
		}
		const savedDate = sessionStorage.getItem(storageName);

		if (!savedDate) {
			sessionStorage.setItem(storageName, JSON.stringify(initTime.getTime()));
		}

		var id = setInterval(() => {
			var left =
				(savedDate ? (new Date() - parseInt(savedDate, 10)) : (new Date() - initTime));
			setCount(left);
			showTimer(left);
			if (left <= 0) {
				setTime("00:00:00");
				clearInterval(id);
			}
		}, 1);
		return () => clearInterval(id);
	}, [start]);

	return <>{time}</>;
}

export default CountUpTimer;
