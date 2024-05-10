"use client";

import { useFormStatus } from "react-dom";

function Loader({ text }: { readonly text: string }) {
  return (
    <div className="flex items-center space-x-2">
      <span className="loading loading-spinner loading-xs"></span> <p>{text}</p>
    </div>
  );
}

interface SubmitButtonProps {
  text: string;
  loadingText: string;
  className?: string;
  loading?: boolean;
}

export function SubmitButton({
  text,
  loadingText,
  loading,
  className,
}: Readonly<SubmitButtonProps>) {
  const status = useFormStatus();

  return (
    <button
      type="submit"
      disabled={status.pending || loading}
      className={`btn ${className}`}
    >
      {status.pending || loading ? <Loader text={loadingText} /> : text}
    </button>
  );
}
