import { useAlertSotre } from '@/store/alert';
import { storeToRefs } from 'pinia';

export const useAlert = () => {
	const { alerts } = storeToRefs(useAlertSotre());
	const { vAlert, vSuccess } = useAlertSotre();
	return {
		alerts,
		vAlert,
		vSuccess,
	};
};
