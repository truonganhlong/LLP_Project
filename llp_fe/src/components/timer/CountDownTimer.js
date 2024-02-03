import moment from "moment/moment";
import PropTypes from "prop-types";
import { useEffect, useState } from "react";
import Countdown from "react-countdown";

//----------------------------------------------------------------

CountDownTimer.propTypes = {
	deadline: PropTypes.number,
	formatClock: PropTypes.string,
	completedCompoment: PropTypes.node,
	completedFunction: PropTypes.func,
	storageName: PropTypes.string,
};

export default function CountDownTimer({
	deadline,
	formatClock,
	completedCompoment,
	completedFunction,
	storageName,
}) {
	const [data, setData] = useState({ date: Date.now(), delay: deadline });
	const [wantedDelay, setWantedDelay] = useState(deadline);

	const formatTime = ({ total }) => {
		const milliseconds = Math.floor((total % 1000) / 10)
			.toString()
			.padStart(2, "0");
		const second = Math.floor((total / 1000) % 60)
			.toString()
			.padStart(2, "0");
		const minute = Math.floor((total / 1000 / 60) % 60)
			.toString()
			.padStart(2, "0");
		const hour = Math.floor(total / 1000 / 60 / 60).toString();
		const stringDate = hour.padStart(2, "0") + ":" + minute + ":" + second;

		return stringDate;
	};

	const renderer = ({ completed, props, ...other }) => {
		const { total } = other;
		const { date } = props;
		if (completed) {
			completedFunction();
			return <>{completedCompoment ? completedCompoment : "00:00:00"}</>;
		} else {
			return <>{formatTime({ total: total })}</>;
		}
	};

	useEffect(() => {
		const savedDate = sessionStorage.getItem(storageName);

		if (savedDate != null && !isNaN(savedDate)) {
			const currentTime = Date.now();
			const delta = parseInt(savedDate, 10) - currentTime;

			if (delta > wantedDelay) {
				if (sessionStorage.getItem(storageName).length > 0)
					sessionStorage.removeItem(storageName);
			} else {
				setData({ date: currentTime, delay: delta });
			}
		}
	}, []);

	return (
		<Countdown
			date={data.date + data.delay}
			renderer={renderer}
			onStart={(delta) => {
				//Save the end date
				if (sessionStorage.getItem(storageName) == null)
					sessionStorage.setItem(
						storageName,
						JSON.stringify(data.date + data.delay)
					);
			}}
			onComplete={() => {
				if (sessionStorage.getItem(storageName) != null)
				sessionStorage.removeItem(storageName);
			}}
		/>
	);
}
