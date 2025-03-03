export const formatYYYYMMDDKr = (dateStr) => {
    if (!dateStr) return "";

    const date = new Date(dateStr);
    return new Intl.DateTimeFormat('ko-KR', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    }).format(date); // from YYYY-MM-DD => to YYYY년 MM월 DD일
};

export const formatSellingPrice = (originalPrice, discountPercentage) => {
    return (originalPrice - (originalPrice * discountPercentage / 100)).toLocaleString();
};

export const formatDiscountAmount = (originalPrice, discountPercentage) => {
    return (originalPrice * discountPercentage / 100).toLocaleString();
}

export const formatDateYYMMKr = (dateStr) => {
    const date = new Date(dateStr);
    return date.getFullYear() + '년 ' + (date.getMonth() + 1) + '월';
}